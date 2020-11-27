package edu.storage.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author l
 * @since 2018-09-12
 */
public class MysqlGenerator {

	// 全局配置 
	private final static String OUTPUT_DIR = "E:/devrepository/fs/hejl-file-storage/hejl-file-storage-pojo/src/main/java";// 生成文件的输出目录                                          

	private final static String AUTHOR = "hejianliang";// 开发人员
	// 数据源配置
	private final static String DATABASE_IP = "106.14.123.81";// 数据库id
	private final static String DATABASE_NAME = "hejl-file-storage";// 数据库名称
	// 包配置
	private final static String PARENT = "edu.storage.pojo";// 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名

	private final static String MODULE_NAME = "cutomer";// 父包模块名
	private final static String TABLE_NAME = "t_customer"; 


	// 自定义基类
	//private final static String SuperEntity = "cn.exrick.xboot.base.XbootBaseEntity";// 所有实体的基类(全类名)
	//private final static String SuperController = "com.baomidou.mybatisplus.samples.generator.common.BaseController";// 所有控制器的基类(全类名)

    /**
     * <p>读取控制台内容</p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
		//String projectPath = "F:/mydevrepository/预研项目/xboot-plus-master/xboot-plus-back";
		gc.setOutputDir(OUTPUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setActiveRecord(true);
     
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+DATABASE_IP+":33306/"+DATABASE_NAME+"?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        //dsc.setDriverName("com.mysql.jdbc.Driver");// JDK7
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");// JDK8
        dsc.setUsername("root");
        dsc.setPassword("Master555_");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setModuleName(MODULE_NAME);
        pc.setParent(PARENT);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return   OUTPUT_DIR + "/" + PARENT.replaceAll("\\.", "/") + "/" + MODULE_NAME + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
               //	 return   OUTPUT_DIR_XML + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML; 
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass(SuperEntity);
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型
        //strategy.setSuperControllerClass(SuperController);
        //strategy.setInclude(scanner("表名"));
        //strategy.setInclude("t_com_batch");
        strategy.setInclude(TABLE_NAME);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
