//package hinatazaka46.smash.repository.util;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import javax.sql.DataSource;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.support.AbstractTestExecutionListener;
//
//public class DBTestExecutionListener extends AbstractTestExecutionListener {
//
//    public static final String DATA_DIR =
//            DBTestExecutionListener.class.getResource("").getFile()
//            + ".." + File.separator + "data" + File.separator;
//
//    private static final File INIT_DATA = new File(DATA_DIR + "db_init.xlsx");
//
//    private static List<File> backupList = new ArrayList<>();
//
//    @Override
//    public void beforeTestMethod(TestContext testContext) {
//        DataSource dataSource = testContext.getApplicationContext().getBean(DataSource.class);
//        Arrays.asList("USER").forEach(filename -> {
//            File file = new File(DATA_DIR + filename + "_back.xls");
//            backupList.add(file);
//            DbUnitUtil.backup(dataSource,file,filename);
//        });
//    }
//}
