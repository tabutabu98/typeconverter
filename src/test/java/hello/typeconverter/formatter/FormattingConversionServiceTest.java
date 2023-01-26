package hello.typeconverter.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() throws Exception {
        //given
        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();

        // 컨버터 등록
        defaultFormattingConversionService.addConverter(new StringToIpPortConverter());
        defaultFormattingConversionService.addConverter(new IpPortToStringConverter());

        // 포맷터 등록
        defaultFormattingConversionService.addFormatter(new MyNumberFormatter());

        //when
        IpPort ipPort = defaultFormattingConversionService.convert("127.0.0.1:8080", IpPort.class);
        String convert1 = defaultFormattingConversionService.convert(1000, String.class);
        Long convert2 = defaultFormattingConversionService.convert("1,000", Long.class);

        //then
        // 포맷터 사용
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        // 컨버터 사용
        assertThat(convert1).isEqualTo("1,000");
        assertThat(convert2).isEqualTo(1000L);
    }
}
