package hinatazaka46.smash.Controller;


import hinatazaka46.smash.dto.CatDto;
import hinatazaka46.smash.service.NyankoService;
import java.util.List;
import java.util.Random;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer.PoolingType;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;


@RestController
@RequestMapping("services/v1/nyanko")
public class UserController {

    private MultiLayerNetwork cnnModel;
    private double[] teacherFeatures;
    private final NyankoService nyankoService;

    public UserController(NyankoService nyankoService) throws IOException {
        this.cnnModel = buildSimpleCNN();
        this.teacherFeatures = getFeaturesFromResource("image.jpg");  // アプリ起動時に特徴量を計算
        this.nyankoService = nyankoService;
    }


    @GetMapping("/type")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public CatDto getCat(@RequestParam(name = "numbers") int[] numbers) {
        // 例として固定のデータを返却
        return this.nyankoService.getCat(numbers);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<CatDto> getAllCat() {
        // 例として固定のデータを返却
        return this.nyankoService.getAllCat();
    }

    @PostMapping("/predict")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public int[] predict(@RequestParam MultipartFile imageFile) throws IOException {
        double[] inputFeatures = getFeaturesFromImage(imageFile.getInputStream());

        int[] answer = new int[8];
        double[] differences = new double[teacherFeatures.length];
        for (int i = 0; i < teacherFeatures.length; i++) {
            differences[i] = Math.abs((teacherFeatures[i] - inputFeatures[i]) * 1000000);
        }

        answer[0] = (int) (100 - (differences[0] / 3));
        answer[1] = (int) (100 - (differences[1] / 5));
        answer[2] = (int) (20 + (differences[2] / 2));
        answer[3] = (int) (100 - (differences[3] / 3));
        answer[4] = (int) (100 - (differences[4] / 3));
        answer[5] = (int) (30 + (differences[5] / 2));
        answer[6] = (int) (100 - (differences[6] / 3));
        answer[7] = (int) (20 + (differences[7] / 2));

        Random random = new Random();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] <= 0) {
                answer[i] = random.nextInt(31);  // 0から30までのランダムな数
            } else if (answer[i] >= 90 & answer[i] != 100) {
                answer[i] = 80 + random.nextInt(11);  // 80から90までのランダムな数
            }
        }

        return answer;
    }

    private double getAverageOfSegment(double[] features, int start, int end) {
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += features[i];
        }
        return sum / (end - start);
    }


    private double[] getFeaturesFromImage(InputStream inputStream) throws IOException {
        BufferedImage resizedImage = resizeImage(inputStream);
        INDArray inputArray = imageToNDArray(resizedImage);
        INDArray output = cnnModel.output(inputArray);
        double[] fullFeatures = output.data().asDouble();

        double[] averagedFeatures = new double[8];
        int segmentSize = fullFeatures.length / 8;

        for (int i = 0; i < 8; i++) {
            averagedFeatures[i] = getAverageOfSegment(fullFeatures, i * segmentSize, (i + 1) * segmentSize);
        }

        return averagedFeatures;
    }

    private double[] getFeaturesFromResource(String resourceName) throws IOException {
        Resource resource = new ClassPathResource(resourceName);
        InputStream is = resource.getInputStream();  // resources下の画像を読み込む
        return getFeaturesFromImage(is);
    }

    private BufferedImage resizeImage(InputStream inputStream) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);
        BufferedImage resizedImage = new BufferedImage(128, 128, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, 128, 128, null);
        return resizedImage;
    }

    private INDArray imageToNDArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        INDArray array = Nd4j.createUninitialized(new int[] {1, 3, height, width});

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                array.putScalar(new int[] {0, 0, j, i}, r);
                array.putScalar(new int[] {0, 1, j, i}, g);
                array.putScalar(new int[] {0, 2, j, i}, b);
            }
        }
        return array.div(255);  // [0, 1]の範囲に正規化
    }

    private MultiLayerNetwork buildSimpleCNN() {
        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);

        ConvolutionLayer layer1 = new ConvolutionLayer.Builder(5, 5)
                .nIn(3)  // カラー画像の3チャンネル
                .stride(1, 1)
                .nOut(8)  // 8つの特徴量を出力するように設定
                .weightInit(WeightInit.XAVIER)
                .build();

        // Second convolution layer
        ConvolutionLayer layer2 = new ConvolutionLayer.Builder(5, 5)
                .nIn(8)
                .stride(1, 1)
                .nOut(16)
                .weightInit(WeightInit.XAVIER)
                .build();

        ConvolutionLayer layer3 = new ConvolutionLayer.Builder(5, 5)
                .nIn(16)
                .stride(1, 1)
                .nOut(16)
                .weightInit(WeightInit.XAVIER)
                .build();

        ConvolutionLayer layer4 = new ConvolutionLayer.Builder(5, 5)
                .nIn(16)
                .stride(1, 1)
                .nOut(8)
                .weightInit(WeightInit.XAVIER)
                .build();

        // Third convolution layer
        ConvolutionLayer layer5 = new ConvolutionLayer.Builder(5, 5)
                .nIn(8)
                .stride(1, 1)
                .nOut(4)
                .weightInit(WeightInit.XAVIER)
                .build();

        SubsamplingLayer poolingLayer = new SubsamplingLayer.Builder(PoolingType.AVG)
                .kernelSize(5, 5)  // 入力画像のサイズに基づいて全体を平均化する
                .stride(1, 1)
                .build();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .list()
                .layer(0, layer1)
                .layer(1, layer2)
                .layer(2, layer3)
                .layer(3, layer4)
                .layer(4, layer5)
                .layer(5, poolingLayer)
                .build();

        MultiLayerNetwork cnnModel = new MultiLayerNetwork(conf);
        cnnModel.init();

        return cnnModel;
    }
}
