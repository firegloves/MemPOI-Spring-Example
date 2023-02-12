package it.firegloves.mempoispringexample;

import it.firegloves.mempoi.builder.MempoiBuilder;
import it.firegloves.mempoi.builder.MempoiSheetBuilder;
import it.firegloves.mempoi.domain.MempoiReport;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemPoiService {

    private DataSource dataSource;

    @Autowired
    public MemPoiService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MempoiReport selectFromTestTable() throws SQLException, ExecutionException, InterruptedException {

        final Connection connection = this.dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM test_table");

        final MempoiSheetBuilder mempoiSheetBuilder = MempoiSheetBuilder.aMempoiSheet()
                .withPrepStmt(preparedStatement);

        return MempoiBuilder.aMemPOI()
                .addMempoiSheet(mempoiSheetBuilder)
                .withFile(new File("export_result.xlsx"))
                .build()
                .prepareMempoiReport()
                .get();
    }
}
