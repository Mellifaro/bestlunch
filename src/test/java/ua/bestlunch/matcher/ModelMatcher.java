package ua.bestlunch.matcher;

import org.junit.Assert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.function.Function;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Виктор on 01.12.2016.
 *
 * @param <T> Entity
 * @param <R> testEntity, converter result for compare
 */
public class ModelMatcher<T, R> {
    protected Function<T, R> entityConverter;

    public ModelMatcher(Function<T, R> entityConverter){
        this.entityConverter = entityConverter;
    }

    public void assertEquals(T expected, T actual){
        Assert.assertEquals(entityConverter.apply(expected), entityConverter.apply(actual));
    }

    public void assertListEquals(List<T> expected, List<T> actual){
        Assert.assertEquals(map(expected, entityConverter), map(actual, entityConverter));
    }

    public static <S, T> List<T> map(List<S> list, Function<S, T> converter){
        return list.stream().map(converter);
    }

    public ResultMatcher contentMatcher(T expected){
        return content().string()
    }
}
