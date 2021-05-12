package com.company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Table extends Component {
        public List<ModelClass> readValues() {
            Connection connection=null;
            Statement statement=null;
            ResultSet resultSet=null;
            List<ModelClass> modelList = new ArrayList<>();
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "basanta");
                String query = "SELECT * FROM people";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    ModelClass model = new ModelClass();
                    model.setId(resultSet.getLong("id"));
                    model.setFirstName(resultSet.getString("FirstName"));
                    model.setLastName(resultSet.getString("LastName"));
                    model.setAddress(resultSet.getString("Address"));
                    model.setPhoneNumber(resultSet.getString("Number"));
                    modelList.add(model);
                }

            } catch (Exception exception){

            }
            finally {

                assert connection != null;
                try {  statement.close();
                    resultSet.close();
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }

            return modelList;
        }

        public String getHtml() {
            String tagName = "html";
            String htmlElements = this.head() + this.getBody() + "\n";
            return openAndCloseTag(tagName, htmlElements);
        }

        private String openAndCloseTag(String tagName, String mid) {
            return "\n" + open(tagName) + mid + close(tagName);
        }

        private String head() {
            String tagName = "head";
            String headElements = this.getTitle() + "\n";
            return openAndCloseTag(tagName, headElements);
        }

        private String getTitle() {
            String tagName = "title";
            String text = "HTML Table";
            return openAndCloseTag(tagName, text);
        }

        private String getBody() {
            String tagName = "body";
            String bodyElements = this.getH1() + this.getTable() + "\n";
            return openAndCloseTag(tagName, bodyElements);
        }

        private String getH1() {
            String tagName = "h1";
            String text ="People";
            return openAndCloseTag(tagName, text);
        }

        private String getTable() {
            String tagName = "table";
            String tableElements = this.getTableRow() + "\n" + this.tr();
            return openAndCloseTag(tagName, tableElements);
        }

        private String getTableRow() {
            String tagName = "tr";
            String rowElements = this.getTableHead("Id") + this.getTableHead("First name") + this.getTableHead("Last name") + this.getTableHead("Phone Number") + this.getTableHead("Address") + "\n";
            return openAndCloseTag(tagName, rowElements);
        }

        private String getTableHead(String colName) {
            String tagName = "th";
            return openAndCloseTag(tagName, colName);
        }
    private String getTableData(String data) {
        String tagName = "td";
        return openAndCloseTag(tagName, data);
    }

        private String tr() {
            List<ModelClass> modelList = this.readValues();
            String tagName = "tr";
            StringBuilder line = new StringBuilder();

            modelList.forEach(model -> {

                line.append(open(tagName));
                line.append(this.getTableData(Long.toString(model.getId())));
                line.append(this.getTableData(model.getFirstName()));
                line.append(this.getTableData(model.getLastName()));
                line.append(this.getTableData(model.getPhoneNumber()));
                line.append(this.getTableData(model.getAddress()));
                line.append("\n").append(close(tagName)).append("\n");

            });

            return line.toString();
        }

}
