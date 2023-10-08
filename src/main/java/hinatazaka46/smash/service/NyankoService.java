package hinatazaka46.smash.service;

import hinatazaka46.smash.Domain.CatDescription;
import hinatazaka46.smash.Domain.CatPersonality;
import hinatazaka46.smash.dto.CatDto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class NyankoService {

    public List<CatDto> getAllCat() {
        List<CatDto> catDtos = new ArrayList<CatDto>();
        long num = 0;
        for(CatPersonality catPersonality : CatPersonality.values()) {
            CatDto catDto = new CatDto();
            catDto.setId(num);
            catDto.setType(catPersonality.getDescription());
            catDto.setText((CatDescription.valueOf(catPersonality.toString())).getDescription());
            catDtos.add(catDto);
            num ++;
        }
        return catDtos;
    }

    public CatDto getCat(int[] numbers) {
        CatDto catDto = new CatDto();
        Integer[] sortedIndices = sortIndicesByValues(numbers);

        if (sortedIndices[0] == 0) {
            catDto.setType(CatPersonality.STUDY.getDescription());
            catDto.setText(CatDescription.STUDY.getDescription());
            if (sortedIndices[6] == 1 || sortedIndices[7] == 1) {
                catDto.setType(CatPersonality.BIBIRI.getDescription());
                catDto.setText(CatDescription.BIBIRI.getDescription());
            }
        } else if (sortedIndices[0] == 1) {
            catDto.setType(CatPersonality.NORABOSS.getDescription());
            catDto.setText(CatDescription.NORABOSS.getDescription());
        } else if (sortedIndices[0] == 2) {
            catDto.setType(CatPersonality.CHATTY.getDescription());
            catDto.setText(CatDescription.CHATTY.getDescription());
            if (sortedIndices[6] == 7 || sortedIndices[7] == 7) {
                catDto.setType(CatPersonality.ADVENTURER.getDescription());
                catDto.setText(CatDescription.ADVENTURER.getDescription());
            } else if (sortedIndices[1] == 7 || sortedIndices[2] == 7) {
                catDto.setType(CatPersonality.PLAYFUL.getDescription());
                catDto.setText(CatDescription.PLAYFUL.getDescription());
            }
        } else if (sortedIndices[0] == 3) {
            catDto.setType(CatPersonality.BEAUTY.getDescription());
            catDto.setText(CatDescription.BEAUTY.getDescription());
        } else if (sortedIndices[0] == 4) {
            catDto.setType(CatPersonality.OTTORI.getDescription());
            catDto.setText(CatDescription.OTTORI.getDescription());
        } else if (sortedIndices[0] == 5) {
            catDto.setType(CatPersonality.AMAENBO.getDescription());
            catDto.setText(CatDescription.AMAENBO.getDescription());
        } else if (sortedIndices[0] == 6) {
            catDto.setType(CatPersonality.CHATTY.getDescription());
            catDto.setText(CatDescription.CHATTY.getDescription());
        } else if (sortedIndices[0] == 7) {
            catDto.setType(CatPersonality.MAMA.getDescription());
            catDto.setText(CatDescription.MAMA.getDescription());
        } else if (sortedIndices[0] == 8) {
            catDto.setType(CatPersonality.AMAENBO.getDescription());
            catDto.setText(CatDescription.AMAENBO.getDescription());
        }

        if (sortedIndices[5] == 5 || sortedIndices[6] == 5 ||sortedIndices[7] == 5) {
            if (sortedIndices[5] == 6 || sortedIndices[6] == 6 ||sortedIndices[7] == 6) {
                catDto.setType(CatPersonality.SEKKACHI.getDescription());
                catDto.setText(CatDescription.SEKKACHI.getDescription());
            }
        }

        if (sortedIndices[5] == 1 || sortedIndices[6] == 1 ||sortedIndices[7] == 1) {
            if (sortedIndices[5] == 2 || sortedIndices[6] == 2 ||sortedIndices[7] == 2) {
                catDto.setType(CatPersonality.GUARD.getDescription());
                catDto.setText(CatDescription.GUARD.getDescription());
            }
        }

        if (sortedIndices[7] == 1) {
            catDto.setType(CatPersonality.LONE_WOLF.getDescription());
            catDto.setText(CatDescription.LONE_WOLF.getDescription());
        }

        catDto.setId(1);
        return catDto;
    }

    public static Integer[] sortIndicesByValues(int[] array) {
        return IntStream.range(0, array.length)
                .boxed()
                .sorted(Comparator.comparingInt(i -> -array[i]))
                .toArray(Integer[]::new);
    }
}
