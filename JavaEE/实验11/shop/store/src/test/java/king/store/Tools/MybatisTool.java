package king.store.Tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisTool {
    public static SqlSession getSqlSession() throws IOException {
        //1.获取字节输入流
        Reader configReader = Resources.getResourceAsReader("mybatis-config.xml");
        //2.根据字节输入流构建SqlSessionFactory
        SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sessionFactoryBuilder.build(configReader);
        //3.根据SqlSessionFactory生产一个SqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        configReader.close();
        return sqlSession;
    }
}
