package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {
    @FXML
    private TextField nameCity;

    @FXML
    private Button buttonSearch;

    @FXML
    private Text nameTemperature;

    @FXML
    private Text nameFeeling;

    @FXML
    private Text nameMax;

    @FXML
    private Text nameMin;

    @FXML
    private Text Humidity;

    @FXML
    private Text Pressure;

    @FXML
    private Text Wind;

    @FXML
    void initialize() {
        buttonSearch.setOnAction(event -> {
        String getUserCity = nameCity.getText().trim();

        if(!getUserCity.equals("")) {

            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid=7c216053d518bffab8a1d7d5ed3507cc");

            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                nameTemperature.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                nameFeeling.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                nameMax.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                nameMin.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                Pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                Wind.setText("Скорость ветра: " + obj.getJSONObject("wind").getDouble("speed"));
                Humidity.setText("Влажность: " + obj.getJSONObject("main").getDouble("humidity"));

            }
        }
        });
    }

    private static String getUrlContent(String urlAdress) {
       StringBuffer content = new StringBuffer();

       try {
           URL url = new URL(urlAdress);
           URLConnection urlConn = url.openConnection();

           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
           String line;

           while((line = bufferedReader.readLine()) != null) {
               content.append(line + "\n");
           }
           bufferedReader.close();
       } catch(Exception e) {
            System.out.println("Данный город не был найден!");
       }
       return content.toString();
    }
}
