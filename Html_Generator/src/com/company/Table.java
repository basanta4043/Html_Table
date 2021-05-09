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

        public String html() {
            String tagName = "html";
            String htmlElements = this.head() + this.body() + "\n";
            return openAndCloseTag(tagName, htmlElements);
        }

        private String openAndCloseTag(String tagName, String mid) {
            return "\n" + open(tagName) + mid + close(tagName);
        }

        private String head() {
            String tagName = "head";
            String headElements = this.title() + "\n";
            return openAndCloseTag(tagName, headElements);
        }

        private String title() {
            String tagName = "title";
            String text = "HTML Table";
            return openAndCloseTag(tagName, text);
        }

        private String body() {
            String tagName = "body";
            String bodyElements = this.h1() + this.table() + "\n";
            return openAndCloseTag(tagName, bodyElements);
        }

        private String h1() {
            String tagName = "h1";
            String text ="People";
            return openAndCloseTag(tagName, text);
        }

        private String table() {
            String tagName = "table";
            String tableElements = this.trh() + "\n" + this.tr();
            return openAndCloseTag(tagName, tableElements);
        }

        private String trh() {
            String tagName = "tr";
            String rowElements = this.th("Id") + this.th("First name") + this.th("Last name") + this.th("Phone Number") + this.th("Address") + "\n";
            return openAndCloseTag(tagName, rowElements);
        }

        private String th(String colName) {
            String tagName = "th";
            return openAndCloseTag(tagName, colName);
        }
    private String td(String data) {
        String tagName = "td";
        return openAndCloseTag(tagName, data);
    }

        private String tr() {
            List<ModelClass> modelList = this.readValues();
            String tagName = "tr";
            StringBuilder line = new StringBuilder();

            modelList.forEach(model -> {

                line.append(open(tagName));
                line.append(this.td(Long.toString(model.getId())));
                line.append(this.td(model.getFirstName()));
                line.append(this.td(model.getLastName()));
                line.append(this.td(model.getPhoneNumber()));
                line.append(this.td(model.getAddress()));
                line.append("\n").append(close(tagName)).append("\n");

            });

            return line.toString();
        }

}
