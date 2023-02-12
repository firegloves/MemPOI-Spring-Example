package it.firegloves.mempoispringexample;

import it.firegloves.mempoi.domain.MempoiReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemPoiSpringExampleApplication implements CommandLineRunner {

    @Autowired
    private MemPoiService mempoiService;

    public static void main(String[] args) {
        SpringApplication.run(MemPoiSpringExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final MempoiReport mempoiReport = mempoiService.selectFromTestTable();
        System.out.println(mempoiReport.getFile());

    }
}
