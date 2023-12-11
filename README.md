1. Đầu tiên cần tải các library của JavaFX và SQL Server:
- https://jdk.java.net/javafx21/
- https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16
2. Sau khi tải xong 
-> Thêm các file trong thư mục `javafx-sdk-21.0.1\lib` và các file trong thư mục `sqljdbc_12.4\enu\jars` vào phần Referenced Library
3. Configure lại file launch.json:
  "vmArgs": "--module-path \\"Đường/dẫn/đến/javafx-sdk-21.0.1/lib\\" --add-modules javafx.controls,javafx.fxml"
4. Sử dụng Sql Server restore lại file CNPM.bak
5. Trong file "JDBCUtil.java" sửa phần password trong URL: String url = "jdbc:sqlserver://localhost:1433;databaseName=CNPM;user=sa;password=mật_khẩu_của_bạn;encrypt=true;trustServerCertificate=true;";
