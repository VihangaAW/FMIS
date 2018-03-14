package sample;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//mongoDB import
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.DBObject;

//building


import org.apache.commons.lang.ObjectUtils;
import org.bson.Document;

import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import sun.security.krb5.internal.HostAddress;

//Jasper Reports
import net.sf.jasperreports.engine.JRException;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Main extends Application {

    //Public Declarations
    Stage window, childWindow, mStage, aStage, asStage, rStage, cStage, aquesStage;
    Scene login, dashboard, maintenance, request, change, plan, aSettings, aSettingsSQ, CreateAccount;
    public  String passUsername = "";
    public  MongoDatabase database;
    MongoCollection<Document> collection;



    //Private Declarations
    private DatePicker movingDate;
    private DatePicker aqDate;
    private DatePicker currentMovingDate;
    private DatePicker newMovingDate;

    MongoClient mongoClient;


    public void dashoboardMethod(){
        //Dashboard Components
      //  Label labelD1 = new Label ("Dashboard");
      //  labelD1.setFont(Font.font(25));
        Button btBack = new Button("Back");
        Image changeDetailsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeDetails.png");
        ImageView changeDetailsImageView = new ImageView(changeDetailsImage);
        //changeDetailsImageView.setFitHeight(204);
        //changeDetailsImageView.setFitWidth(220);
        Image maintenanceImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/maintenanceLog.png");
        ImageView maintenanceImageView = new ImageView(maintenanceImage);
        Image accountSettingsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/AccountSettings.png");
        ImageView accountSettingsImageView = new ImageView(accountSettingsImage);
        Image buildingImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/viewBUildingPLan.png");
        ImageView buildingImageView = new ImageView(buildingImage);
        Image officeMoveImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/requestOfficeMove.png");
        ImageView officeMoveImageView = new ImageView(officeMoveImage);
        Image newAccountImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/newUser.png");
        ImageView newAccountImageView = new ImageView(newAccountImage);
        Image reportImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/assetReport.png");
        ImageView reportImageView = new ImageView(reportImage);
        Image furnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/addFurniture.png");
        ImageView furnituremageView = new ImageView(furnitureImage);
        Image addFurnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeFurniture.png");
        ImageView addFurnituremageView = new ImageView(addFurnitureImage);




        btBack.setOnAction(e -> window.setScene(login));
        Button btmntCall = new Button("",maintenanceImageView);
        btmntCall.setPadding(Insets.EMPTY);
        btmntCall.setOnAction(e-> {
            maintenance();
            System.out.println("after scene");
        });
        Button btReports    = new Button("", reportImageView);
        btReports.setPadding(Insets.EMPTY);
        Button btAccountSettings = new Button("",accountSettingsImageView );
        btAccountSettings.setPadding(Insets.EMPTY);
        btAccountSettings.setOnAction(e->{
            System.out.println(passUsername);
            accountSettings();
        });

        Button btreq = new Button("", officeMoveImageView);
        btreq.setPadding(Insets.EMPTY);
        btreq.setOnAction(e-> {
            requestOfficeMove();
        });
        Button btchng = new Button("", changeDetailsImageView);
        btchng.setPadding(Insets.EMPTY);
        btchng.setOnAction(e-> {
            changeDetails();
        });
        Button btplan = new Button("", buildingImageView);
        btplan.setPadding(Insets.EMPTY);
        btplan.setOnAction(e-> {
            buildingPlan();
        });
        Button btCreateAccount = new Button("", newAccountImageView);
        btCreateAccount.setPadding(Insets.EMPTY);
        btCreateAccount.setOnAction(e->{
            accountCreate();
        });
        Button btAddFurniture = new Button("",furnituremageView);
        btAddFurniture.setPadding(Insets.EMPTY);
        btAddFurniture.setOnAction(e-> {
            addFurniture();
        });
        Button btChangeFurniture = new Button("",addFurnituremageView);
        btChangeFurniture.setPadding(Insets.EMPTY);
        btChangeFurniture.setOnAction(e-> {
            changeFurniture();
        });
        //ImageView iv1=new ImageView(playI);
        //iv1.setFitHeight(67);
        //iv1.setFitWidth(69);


        GridPane gridDashboard = new GridPane();
        gridDashboard.setStyle("-fx-background-color: #161616;");
        gridDashboard.setHgap(10);
        gridDashboard.setVgap(10);
        //gridDashboard.add(labelD1,0,0,1,1);
        gridDashboard.add(btmntCall,0,1,1,1);
        gridDashboard.add(btreq,1,1,1,1);
        gridDashboard.add(btchng,2,1,1,1);
        gridDashboard.add(btChangeFurniture,4,1,1,1);
        gridDashboard.add(btplan,3,1,1,1);
        gridDashboard.add(btAccountSettings,0,2,1,1);
        gridDashboard.add(btCreateAccount,1,2,1,1);
        gridDashboard.add(btReports,2,2,1,1);
        gridDashboard.add(btAddFurniture,3,2,1,1);

        //Dashoboard layout
        // HBox layoutDashboard = new HBox(30);
        //layoutDashboard.getChildren().addAll(labelD1,btBack, btmntCall,btreq,btchng, btplan);
        dashboard = new Scene(gridDashboard, 1140,440);
        window.setScene(dashboard);
        window.setTitle("Dashboard");
        window.setResizable(false);
    }



    public void dashoboardMethodMedium(){
        //Dashboard Components
        //  Label labelD1 = new Label ("Dashboard");
        //  labelD1.setFont(Font.font(25));
        Button btBack = new Button("Back");
        Image changeDetailsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeDetails.png");
        ImageView changeDetailsImageView = new ImageView(changeDetailsImage);
        //changeDetailsImageView.setFitHeight(204);
        //changeDetailsImageView.setFitWidth(220);
        Image maintenanceImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/maintenanceLog.png");
        ImageView maintenanceImageView = new ImageView(maintenanceImage);
        Image accountSettingsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/AccountSettings.png");
        ImageView accountSettingsImageView = new ImageView(accountSettingsImage);
        Image buildingImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/viewBUildingPLan.png");
        ImageView buildingImageView = new ImageView(buildingImage);
        Image officeMoveImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/requestOfficeMove.png");
        ImageView officeMoveImageView = new ImageView(officeMoveImage);
        Image newAccountImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/newUser.png");
        ImageView newAccountImageView = new ImageView(newAccountImage);
        Image reportImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/assetReport.png");
        ImageView reportImageView = new ImageView(reportImage);
        Image furnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/addFurniture.png");
        ImageView furnituremageView = new ImageView(furnitureImage);
        Image addFurnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeFurniture.png");
        ImageView addFurnituremageView = new ImageView(addFurnitureImage);


        btBack.setOnAction(e -> window.setScene(login));
        Button btmntCall = new Button("",maintenanceImageView);
        btmntCall.setPadding(Insets.EMPTY);
        btmntCall.setOnAction(e-> {
            maintenance();
            System.out.println("after scene");
        });
        Button btReports    = new Button("", reportImageView);
        btReports.setPadding(Insets.EMPTY);
        btReports.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reports");
            alert.setHeaderText("Access Denided");
            alert.setContentText("You have no permission to access this area. Please contact admin");
            alert.showAndWait();
        });
        Button btAccountSettings = new Button("",accountSettingsImageView );
        btAccountSettings.setPadding(Insets.EMPTY);
        btAccountSettings.setOnAction(e->{
            System.out.println(passUsername);
            accountSettings();
        });

        Button btreq = new Button("", officeMoveImageView);
        btreq.setPadding(Insets.EMPTY);
        btreq.setOnAction(e-> {
            requestOfficeMove();
        });
        Button btchng = new Button("", changeDetailsImageView);
        btchng.setPadding(Insets.EMPTY);
        btchng.setOnAction(e-> {
            changeDetails();
        });
        Button btplan = new Button("", buildingImageView);
        btplan.setPadding(Insets.EMPTY);
        btplan.setOnAction(e-> {
            buildingPlan();
        });
        Button btCreateAccount = new Button("", newAccountImageView);
        btCreateAccount.setPadding(Insets.EMPTY);
        btCreateAccount.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New User");
            alert.setHeaderText("Access Denided");
            alert.setContentText("You have no permission to access this area. Please contact admin");
            alert.showAndWait();
        });
        Button btAddFurniture = new Button("",furnituremageView);
        btAddFurniture.setPadding(Insets.EMPTY);
        btAddFurniture.setOnAction(e-> {
            addFurniture();
        });
        Button btChangeFurniture = new Button("",addFurnituremageView);
        btChangeFurniture.setPadding(Insets.EMPTY);
        btChangeFurniture.setOnAction(e-> {
            changeFurniture();
        });
        //ImageView iv1=new ImageView(playI);
        //iv1.setFitHeight(67);
        //iv1.setFitWidth(69);


        GridPane gridDashboard = new GridPane();
        gridDashboard.setStyle("-fx-background-color: #161616;");
        gridDashboard.setHgap(10);
        gridDashboard.setVgap(10);
        //gridDashboard.add(labelD1,0,0,1,1);
        gridDashboard.add(btmntCall,0,1,1,1);
        gridDashboard.add(btreq,1,1,1,1);
        gridDashboard.add( btchng,2,1,1,1);
        gridDashboard.add(btplan,3,1,1,1);
        gridDashboard.add(btAccountSettings,0,2,1,1);
        gridDashboard.add(btCreateAccount,1,2,1,1);
        gridDashboard.add(btReports,2,2,1,1);
        gridDashboard.add(btAddFurniture,3,2,1,1);
        gridDashboard.add(btChangeFurniture,4,1,1,1);

        //Dashoboard layout
        // HBox layoutDashboard = new HBox(30);
        //layoutDashboard.getChildren().addAll(labelD1,btBack, btmntCall,btreq,btchng, btplan);
        dashboard = new Scene(gridDashboard, 1140,440);
        window.setScene(dashboard);
        window.setTitle("Dashboard");
        window.setResizable(false);
    }






    public void dashoboardMethodLow(){
        //Dashboard Components
        //  Label labelD1 = new Label ("Dashboard");
        //  labelD1.setFont(Font.font(25));
        Button btBack = new Button("Back");
        Image changeDetailsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeDetails.png");
        ImageView changeDetailsImageView = new ImageView(changeDetailsImage);
        //changeDetailsImageView.setFitHeight(204);
        //changeDetailsImageView.setFitWidth(220);
        Image maintenanceImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/maintenanceLog.png");
        ImageView maintenanceImageView = new ImageView(maintenanceImage);
        Image accountSettingsImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/AccountSettings.png");
        ImageView accountSettingsImageView = new ImageView(accountSettingsImage);
        Image buildingImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/viewBUildingPLan.png");
        ImageView buildingImageView = new ImageView(buildingImage);
        Image officeMoveImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/requestOfficeMove.png");
        ImageView officeMoveImageView = new ImageView(officeMoveImage);
        Image newAccountImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/newUser.png");
        ImageView newAccountImageView = new ImageView(newAccountImage);
        Image reportImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/assetReport.png");
        ImageView reportImageView = new ImageView(reportImage);
        Image furnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/addFurniture.png");
        ImageView furnituremageView = new ImageView(furnitureImage);
        Image addFurnitureImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeFurniture.png");
        ImageView addFurnituremageView = new ImageView(addFurnitureImage);

        btBack.setOnAction(e -> window.setScene(login));
        Button btmntCall = new Button("",maintenanceImageView);
        btmntCall.setPadding(Insets.EMPTY);
        btmntCall.setOnAction(e-> {
            maintenance();
            System.out.println("after scene");
        });
        Button btReports    = new Button("", reportImageView);
        btReports.setPadding(Insets.EMPTY);
        btReports.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reports");
            alert.setHeaderText("Access Denided");
            alert.setContentText("You have no permission to access this area. Please contact admin");
            alert.showAndWait();
        });
        Button btAccountSettings = new Button("",accountSettingsImageView );
        btAccountSettings.setPadding(Insets.EMPTY);
        btAccountSettings.setOnAction(e->{
            System.out.println(passUsername);
            accountSettings();
        });

        Button btreq = new Button("", officeMoveImageView);
        btreq.setPadding(Insets.EMPTY);
        btreq.setOnAction(e-> {
            requestOfficeMove();
        });
        Button btchng = new Button("", changeDetailsImageView);
        btchng.setPadding(Insets.EMPTY);
        btchng.setOnAction(e-> {
            changeDetails();
        });
        Button btplan = new Button("", buildingImageView);
        btplan.setPadding(Insets.EMPTY);
        btplan.setOnAction(e-> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Building Plans");
            alert.setHeaderText("Access Denided");
            alert.setContentText("You have no permission to access this area. Please contact admin");
            alert.showAndWait();
        });
        Button btCreateAccount = new Button("", newAccountImageView);
        btCreateAccount.setPadding(Insets.EMPTY);
        btCreateAccount.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New User");
            alert.setHeaderText("Access Denided");
            alert.setContentText("You have no permission to access this area. Please contact admin");
            alert.showAndWait();
        });
        Button btAddFurniture = new Button("",furnituremageView);
        btAddFurniture.setPadding(Insets.EMPTY);
        btAddFurniture.setOnAction(e-> {
            addFurniture();
        });
        Button btChangeFurniture = new Button("",addFurnituremageView);
        btChangeFurniture.setPadding(Insets.EMPTY);
        btChangeFurniture.setOnAction(e-> {
            changeFurniture();
        });
        //ImageView iv1=new ImageView(playI);
        //iv1.setFitHeight(67);
        //iv1.setFitWidth(69);


        GridPane gridDashboard = new GridPane();
        gridDashboard.setStyle("-fx-background-color: #161616;");
        gridDashboard.setHgap(10);
        gridDashboard.setVgap(10);
        //gridDashboard.add(labelD1,0,0,1,1);
        gridDashboard.add(btmntCall,0,1,1,1);
        gridDashboard.add(btreq,1,1,1,1);
        gridDashboard.add( btchng,2,1,1,1);
        gridDashboard.add(btplan,3,1,1,1);
        gridDashboard.add(btAccountSettings,0,2,1,1);
        gridDashboard.add(btCreateAccount,1,2,1,1);
        gridDashboard.add(btReports,2,2,1,1);
        gridDashboard.add(btAddFurniture,3,2,1,1);
        gridDashboard.add(btChangeFurniture,4,1,1,1);

        //Dashoboard layout
        // HBox layoutDashboard = new HBox(30);
        //layoutDashboard.getChildren().addAll(labelD1,btBack, btmntCall,btreq,btchng, btplan);
        dashboard = new Scene(gridDashboard,  1140,440);
        window.setScene(dashboard);
        window.setTitle("Dashboard");
        window.setResizable(false);
    }









    public void accountCreate() {
        //New Account Creation Components
        Stage aStage = new Stage();
        Label labelAcc1 = new Label("Create a new Account");
        labelAcc1.setFont(Font.font(25));
        Label labelAcc2 = new Label("Username ");
        Label labelAcc3 = new Label("Password");
        Label labelAcc4 = new Label("Re type Password");
        Label labelAcc5 = new Label("Permision Type");
        Label labelAcc6 = new Label("Security Question");
        Label labelAcc7 = new Label("Answer");
        TextField txtAccUsername = new TextField();
        TextField txtAccPassword = new TextField();
        TextField txtAccRePassword = new TextField();
        TextField txtAccQuestion = new TextField();
        TextField txtAccAnswer = new TextField();
        Button btAccSave = new Button("Create Account");
        ObservableList<String> optionPermisisonType = FXCollections.observableArrayList (
                "High", "Medium", "Low");
        final ComboBox listPermisisonType = new ComboBox(optionPermisisonType);
        btAccSave.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("userAccount");

            FindIterable<Document> iterable = database.getCollection("userAccount").find(new Document("username", txtAccUsername.getText()));
            boolean userFound =  iterable.first() != null;

            if(userFound==false) {
                System.out.println(userFound);
                System.out.println(iterable.first() != null);
                Document newAccount = new Document("username", txtAccUsername.getText()).append("password", txtAccPassword.getText())
                        .append("permissionType", listPermisisonType.getValue()).append("securityQ", txtAccQuestion.getText()).append("answer", txtAccAnswer.getText());
                collection.insertOne(newAccount);

                List<Document> documents = (List<Document>) collection.find().into(
                            new ArrayList<Document>());

                for (Document document : documents) {
                    System.out.println(document);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("New user");
                alert.setHeaderText(txtAccUsername.getText()+" user is created.");
                alert.setContentText(txtAccUsername.getText()+" user is created.");

                alert.showAndWait();
            }
            else{
                System.out.println(userFound);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Exits");
                alert.setHeaderText("User Account already exits!");
                //alert.setHeaderText(txtUsername.getText());
                alert.setContentText("Ooops, the username is exits, try a different user name");

                alert.showAndWait();
            }
        });
        GridPane newAccount = new GridPane();
        newAccount.setVgap(10);
        newAccount.setPadding(new Insets(40,10,10,10));
        newAccount.add(labelAcc1,0,0,1,1);
        newAccount.add(labelAcc2,0,1,1,1);
        newAccount.add(labelAcc3,0,2,1,1);
        newAccount.add(labelAcc4,0,3,1,1);
        newAccount.add(labelAcc5,0,4,1,1);
        newAccount.add(txtAccPassword,1,2,1,1);
        newAccount.add(txtAccUsername,1,1,1,1);
        newAccount.add(txtAccRePassword,1,3,1,1);
        newAccount.add(listPermisisonType,1,4,1,1);
        newAccount.add(labelAcc6,0,5,1,1);
        newAccount.add(labelAcc7,0,6,1,1);
        newAccount.add(txtAccQuestion,1,5,1,1);
        newAccount.add(txtAccAnswer,1,6,1,1);
        newAccount.add(btAccSave,1,7,1,1);
        CreateAccount = new Scene(newAccount,450,400);
        newAccount.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");
        aStage.setScene(CreateAccount);
        aStage.setTitle("Create Account");
        aStage.setResizable(false);
        aStage.show();

    }











    public void addFurniture(){
        //Request an office move Components
        Stage fStage = new Stage();
        GridPane gridpaneFurniture = new GridPane();
        Label labelR1 = new Label("Add Furniture");
        labelR1.setFont(Font.font(25));
        // Label labelR2 = new Label("Request By");
        Label labelR3 = new Label("Barcode No");
        Label labelR4 = new Label("Description");
        Label labelR5 = new Label("Acquisition Date");
        Label labelR6 = new Label("Cost");
        Label labelR7 = new Label("Building No");
        Label labelR8 = new Label("Floor No");
        Label labelR9 = new Label("Room No");
        Label labelR10 = new Label("Item ID");
        Label labelR11 = new Label("Supplier");

        TextField barcodeNo = new TextField();
        TextField cost = new TextField();
        TextField buildingNo = new TextField();
        TextField floorNo = new TextField();
        TextField roomNo = new TextField();
        TextField itemID = new TextField();
        TextField aqDateText = new TextField();
        TextField supplier = new TextField();


        ObservableList<String> description = FXCollections.observableArrayList (
                "Mel Desk", "Pine Desk", "Mel Bookcase","Pine Bookcase","Gas-lift Chair","Fixed frame Chair");

        //desks, chairs, filing cabinets and bookcasesDesks, filing cabinets and bookcases are one standard size,
        // differentiated only by their construction material (chipboard/melamine or stained pine in the case of
        // desks or bookcases, metal or stained pine in the case of filing cabinets). Chairs are of two types,
        // viz., gas lift and fixed frame. The purchase cost, purchase date and supplier for each item is recorded
        // in the system, as well as the key number if the item has a key. Assets can be entered, updated, marked
        // as beyond repair or marked as sold.


        final ComboBox listDescription = new ComboBox(description);
        //
        Button btAdd = new Button("Add Item");
        Button btRCancel = new Button("Cancel");
        btAdd.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btRCancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");

        gridpaneFurniture.setHgap(10);
        gridpaneFurniture.setVgap(10);
        gridpaneFurniture.setPadding((new Insets(40,5,10,10)));
        gridpaneFurniture.add(labelR10,0,1,1,1);
        gridpaneFurniture.add(labelR1,0,0,1,1);
        gridpaneFurniture.add(labelR3,0,2,1,1);
        gridpaneFurniture.add(labelR4,0,3,1,1);
        gridpaneFurniture.add(labelR5,0,4,1,1);
        gridpaneFurniture.add(labelR6,0,5,1,1);
        gridpaneFurniture.add(labelR7,0,6,1,1);
        gridpaneFurniture.add(labelR8,0,7,1,1);
        gridpaneFurniture.add(labelR9,0,8,1,1);
        gridpaneFurniture.add(labelR11,0,9,1,1);

        gridpaneFurniture.add(btAdd,0,10,1,1);
        gridpaneFurniture.add(btRCancel,    1,10,1,1);
        gridpaneFurniture.add(itemID,       1,1,1,1);
        gridpaneFurniture.add(barcodeNo,    1,2,1,1);
        gridpaneFurniture.add(listDescription,1,3,1,1);
        gridpaneFurniture.add(aqDateText,       1,4,1,1);
        gridpaneFurniture.add(cost,         1, 6,1,1);
        gridpaneFurniture.add(buildingNo,   1,5,1,1);
        gridpaneFurniture.add(floorNo,       1,7,1,1);
        gridpaneFurniture.add(roomNo,       1,8,1,1);
        gridpaneFurniture.add(supplier,1,9,1,1);

        btRCancel.setOnAction(e->{
            fStage.close();
        });
//Need to add moving date
        btAdd.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("addFurnitureItems");
            Document newM = new Document("requestedBy", passUsername).append("Barcode", barcodeNo.getText())
                    .append("Description",listDescription.getValue()).append("Cost",cost.getText()).append("BuildinigNo",buildingNo.getText())
                    .append("RoomNo", roomNo.getText()).append("itemID",itemID.getText()).append("AcquisitionDate",aqDateText.getText()).append("FloorNo", floorNo.getText()).append("Supplier", supplier.getText());
            collection.insertOne(newM);
            fStage.close();
        });




        //Request an office move Layout
        // VBox layoutRequest = new VBox(20);
        //layoutRequest.getChildren().addAll(labelR1);
        request = new Scene(gridpaneFurniture,470,440);
        gridpaneFurniture.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/addFBG.png\")");

        fStage.setScene(request);
        fStage.setTitle("Office Move");
        fStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        fStage.setResizable(false);
        fStage.show();

    }




































    public void changeFurniture(){

        //Change Details Components
        Stage cfStage = new Stage();
        GridPane gridpaneChange = new GridPane();
        Label labelC1 = new Label("Change Furniture Details");
        labelC1.setFont(Font.font(25));
        Label labelC2 = new Label("Item ID");
        Label labelC3 = new Label("Barcode");
        Label labelC4 = new Label("Description");
        Label labelC5 = new Label("Aquisition Date");
        Label labelC6 = new Label("Cost");
        Label labelC7 = new Label("Building No");
        Label labelC8 = new Label("Floor No");
        Label labelC9 = new Label("Room No");
        Label labelC10 = new Label("Current State");
        Button btsearch = new Button("Search");
        Button btsave = new Button("Save Changes");
        Button btCCancel = new Button("Cancel");
        btCCancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btsave.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btsearch.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        TextField barcodeNo = new TextField();
        TextField cost = new TextField();
        TextField buildingNo = new TextField();
        TextField floorNo = new TextField();
        TextField roomNo = new TextField();
        TextField itemID = new TextField();
        TextField aqDateText = new TextField();
        ObservableList<String> description = FXCollections.observableArrayList (
                "Mel Desk", "Pine Desk", "Mel Bookcase","Pine Bookcase","Gas-lift Chair","Fixed frame Chair");

        //desks, chairs, filing cabinets and bookcasesDesks, filing cabinets and bookcases are one standard size,
        // differentiated only by their construction material (chipboard/melamine or stained pine in the case of
        // desks or bookcases, metal or stained pine in the case of filing cabinets). Chairs are of two types,
        // viz., gas lift and fixed frame. The purchase cost, purchase date and supplier for each item is recorded
        // in the system, as well as the key number if the item has a key. Assets can be entered, updated, marked
        // as beyond repair or marked as sold.


        final ComboBox listDescription = new ComboBox(description);
        ObservableList<String> state = FXCollections.observableArrayList (
                "In Use","Repair","Sold");
        final ComboBox listState = new ComboBox(state);
        gridpaneChange.setHgap(10);
        gridpaneChange.setVgap(10);
        gridpaneChange.setPadding(new Insets(40,10,10,10));
        gridpaneChange.add(labelC1,0,0,1,1);
        gridpaneChange.add(labelC2,0,1,1,1);
        gridpaneChange.add(labelC3,0,2,1,1);
        gridpaneChange.add(labelC4,0,3,1,1);
        gridpaneChange.add(labelC5,0,4,1,1);
        gridpaneChange.add(labelC6,0,5,1,1);
        gridpaneChange.add(labelC7,0,6,1,1);
        gridpaneChange.add(labelC8,0,7,1,1);
        gridpaneChange.add(labelC9,0,8,1,1);
        gridpaneChange.add(labelC10,0,9,1,1);

        gridpaneChange.add(itemID,1,1,1,1);
        gridpaneChange.add(barcodeNo,1,2,1,1);
        gridpaneChange.add(listDescription,1,3,1,1);
        gridpaneChange.add(aqDateText,1,4,1,1);
        gridpaneChange.add(cost,1,5,1,1);
        gridpaneChange.add(buildingNo,1,6,1,1);
        gridpaneChange.add(floorNo,1,7,1,1);
        gridpaneChange.add(roomNo,1,8,1,1);
        gridpaneChange.add(listState,1,9,1,1);
        gridpaneChange.add(btsave,1,10,1,1);
        gridpaneChange.add(btsearch,3,1,1,1);
        gridpaneChange.add(btCCancel,2,10,1,1);

        btsearch.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("addFurnitureItems");

            Document document1 = collection.find(eq("itemID",itemID.getText())).first();
            barcodeNo.setText((String) document1.get("Barcode"));
            cost.setText((String)document1.get("Cost"));
            buildingNo.setText((String) document1.get("BuildinigNo"));
            floorNo.setText((String)document1.get("FloorNo"));
            roomNo.setText((String) document1.get("RoomNo"));
            itemID.setText((String)document1.get("itemID"));
            listDescription.getSelectionModel().select(document1.get("Description"));
            aqDateText.setText((String)document1.get("AcquisitionDate"));
        });


        //{Barcode:"1234567891023",Description:"Mel Desk",AcquisitionDate:"17/06/2012",
        // Cost:"200",BuildinigNo:"01",FloorNo:"1",RoomNo:"129G",requestedBy:"Vihanga",itemID:"1231"})


        btsave.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            //// MongoCollection<Document> collection = database.getCollection("officeMove");
            //collection.updateOne(eq("mOfficNo","001A"),new Document("$set",new Document("mBuildingNo","02")));
            ////collection.updateOne(eq("mOfficNo",changeCurrentOfficeNo.getText()),new Document("$set",new Document("mBuildingNo",changeNewBuildingNo.getText())));
            //// collection.updateOne(eq("mOfficNo",changeCurrentOfficeNo.getText()),new Document("$set",new Document("mOfficNo",changeNewOfficeNo.getText())));
            MongoCollection<Document> collection = database.getCollection("addFurnitureItems");
            Document document1 = collection.find(eq("itemID",itemID.getText())).first();
            System.out.println(passUsername);
            System.out.println((String) document1.get("requestedBy"));

            if(passUsername.equals((String)document1.get("requestedBy"))){
                System.out.println("after if");
//               if(changeNewBuildingNo.getText().equals(document1.get("mBuildingNo")) && changeNewOfficeNo.getText().equals("mRoomNo") && newMovingDate.getValue().equals("")){
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("Barcode",barcodeNo.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("Cost",cost.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("BuildinigNo",buildingNo.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("FloorNo",floorNo.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("RoomNo",roomNo.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("itemID",itemID.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("Description",listDescription.getValue())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("AcquisitionDate",aqDateText.getText())));
                collection.updateOne(eq("itemID",itemID.getText()),new Document("$set",new Document("State",listState.getValue())));
                System.out.println("after update");
                cfStage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Access Denied");
                alert.setHeaderText("Access Denied");
                alert.setContentText("Ooops, You have no authority to change the current record details. To change, please contact person who requested this move.");
                alert.showAndWait();

            }






        });
        btCCancel.setOnAction(e->{
            cfStage.close();
        });

        //Change Details Layout
        //VBox layoutChange = new VBox(20);
        //layoutChange.getChildren().addAll(labelC1);
        change = new Scene(gridpaneChange,700,455);
        gridpaneChange.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/changeFBG.png\")");

        cfStage.setScene(change);
        cfStage.setTitle("Change Details");
        cfStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        cfStage.setResizable(false);
        cfStage.show();
    }




























    public void maintenance(){
System.out.println("manitenane");
        //Maintenance Components
        Stage mStage = new Stage();
        GridPane gridMaintenance = new GridPane();
        Label labelM1 = new Label("Maintenance");
        Label labelM2 = new Label("Logged By");
        //Label labelM3 = new Label(txtUsername.getText());
        Label labelM3 = new Label("HEllo");
        Label labelM4 = new Label("Call For");
        Label labelM5 = new Label("Building");
        Label labelM6 = new Label("Room No");
        Label labelM7 = new Label("Status");
        TextField txtMBuilding = new TextField();
        TextField txtMRoomNo = new TextField();
        TextField txtMStatus = new TextField();
        Button btOk = new Button("OK");
        Button btMCancel = new Button("Cancel");
        btOk.setStyle(" -fx-padding: 10 30 10 30;");
        btMCancel.setStyle("-fx-padding: 10 30 10 30;");

        ObservableList<String> options = FXCollections.observableArrayList (
                "Office to be Opened", "Lighting to be replaced", "Carpet to be cleaned");
        final ComboBox listMaintenence = new ComboBox(options);
        ObservableList<String> optionsStatus = FXCollections.observableArrayList (
                "Logged", "Actioned", "Cancelled");
        final ComboBox listStatus = new ComboBox(optionsStatus);
        btMCancel.setOnAction(e->{
            mStage.close();
        });
        System.out.println("manitenane before click button");

        btOk.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("maintenanceLog");
            Document newM = new Document("user", passUsername).append("callType", listMaintenence.getValue())
                    .append("buildingNo",txtMBuilding.getText()).append("roomNo",txtMRoomNo.getText()).append("status",listStatus.getValue());
            collection.insertOne(newM);
            List<Document> documents = (List<Document>) collection.find().into(
                    new ArrayList<Document>());

            for(Document document : documents){
                System.out.println(document);
            }
        });
        System.out.println("before gridthing");

        gridMaintenance.setHgap(10);
        gridMaintenance.setVgap(10);
        gridMaintenance.setPadding(new Insets(40,10,10,10));
        gridMaintenance.add(labelM4,0,1,1,1);
        gridMaintenance.add(listMaintenence,1,1,1,1);
        gridMaintenance.add(txtMBuilding,1,2);
        gridMaintenance.add(labelM5,0,2,1,1);
        gridMaintenance.add(txtMRoomNo,3,2);
        gridMaintenance.add(labelM6,2,2,1,1);
        gridMaintenance.add(labelM7,0,3,1,1);
        gridMaintenance.add(listStatus,1,3,1,1);
        gridMaintenance.add(btOk,0,4,1,1);
        gridMaintenance.add(btMCancel,1,4,1,1);
        gridMaintenance.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/MaintenanceBG.png\")");


        //Maintenance layout
        //VBox layoutMaintenance = new VBox(20);
        //layoutMaintenance.getChildren().addAll(labelM1,labelM2,labelM3,labelM4,labelM5,labelM6,labelM7,listMaintenence);

       Scene maintenance = new Scene(gridMaintenance,580,300);
        System.out.println("mfinal scne");

        mStage.setScene(maintenance);
        mStage.setTitle("Maintenance");
        mStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        mStage.setResizable(false);
        mStage.show();
        System.out.println("afterscne");

    }



    public void accountSettings(){
        //Account Settings Components
        Stage asStage = new Stage();
        Label labelA1 = new Label("Account Settings");
        labelA1.setFont(Font.font(25));
       // Label labelA2 = new Label();
        Label labelA3 = new Label("New Password");
        Label labelA4 = new Label("Retype New Password");
        TextField asUsername = new TextField();
        TextField asPassword = new TextField();
        TextField asRePasswrd = new TextField();
        Button btASave = new Button("Save Changes");
        Button btSQ = new Button("Change Security Question");
        Button btACancel = new Button("Cancel");
        btSQ.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 20 10 20;");
        btASave.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btACancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");

        btSQ.setOnAction(e->{
            System.out.println(passUsername);
            securityQuestion();

        });
        btACancel.setOnAction(e->{
            System.out.println(passUsername);

            childWindow.close();
        });
        btASave.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("userAccount");
            Document getAccountDetails = collection.find(eq("username",passUsername)).first();
           // labelA2.setText((String) getAccountDetails.get("username"));
            if(asPassword.getText().equals(asRePasswrd.getText()))  {
                collection.updateOne(eq("username", passUsername), new Document("$set", new Document("password", asPassword.getText())));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Application Restart");
                alert.setHeaderText("Need Application Restart");
                alert.setContentText("After changing user password, the application should need to restart.");
                alert.showAndWait();
                Platform.exit();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Passwords are doesnt match");
                alert.setHeaderText(passUsername+"Passwords are doesnt match");
                alert.setContentText("Ooops, Passwords are doesnt match! Please enter again");
                alert.showAndWait();
            }


        });
//Change Account Settings
        //VBox layoutAS = new VBox(10);
        GridPane layoutAS = new GridPane();
        layoutAS.setVgap(10);
        layoutAS.setPadding(new Insets(40,10,10,10));
        layoutAS.add(labelA1,0,0,1,1);
        layoutAS.add(labelA3,0,1,1,1);
        layoutAS.add(asPassword,1,1,1,1);
        layoutAS.add(labelA4,0,2,1,1);
        layoutAS.add(asRePasswrd,1,2,1,1);
        layoutAS.add(btSQ,0,3,1,1);
        layoutAS.add(btASave,1,3,1,1);
        layoutAS.add(btACancel,2,3,1,1);
        //layoutAS.getChildren().addAll(labelA4,labelA1,asUsername,labelA2,asPassword,labelA3,asRePasswrd,btASave,btSQ,btACancel);
        layoutAS.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");
        aSettings = new Scene(layoutAS,450,400);
        asStage.setScene(aSettings);
        asStage.setTitle("Account Settings");
        asStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        asStage.setResizable(false);
        asStage.show();

    }




    public void requestOfficeMove(){
        //Request an office move Components
        Stage rStage = new Stage();
        GridPane gridpaneRequest = new GridPane();
        Label labelR1 = new Label("Request an office move");
        labelR1.setFont(Font.font(25));
       // Label labelR2 = new Label("Request By");
        Label labelR8 = new Label("Office No");
        Label labelR3 = new Label("Current Office Room No");
        Label labelR4 = new Label("Current Building No");
        Label labelR5 = new Label("New Office Room No");
        Label labelR6 = new Label("New Building Room No");
        Label labelR7 = new Label("Moving Date");
        TextField OfficeNo = new TextField();
        TextField currentBuildingNo = new TextField();
        TextField newBuildingNo = new TextField();
        TextField newRoomNo = new TextField();
        TextField currentRoomNo = new TextField();
        Button btRequest = new Button("Request");
        Button btRCancel = new Button("Cancel");
        btRequest.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btRCancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        movingDate = new DatePicker();
        gridpaneRequest.setHgap(10);
        gridpaneRequest.setVgap(10);
        gridpaneRequest.setPadding((new Insets(40,5,10,10)));
        gridpaneRequest.add(labelR1,0,0,1,1);
        gridpaneRequest.add(labelR8,0,1,1,1);
        gridpaneRequest.add(labelR3,0,2,1,1);
        gridpaneRequest.add(labelR4,0,3,1,1);
        gridpaneRequest.add(labelR5,0,4,1,1);
        gridpaneRequest.add(labelR6,0,5,1,1);
        gridpaneRequest.add(labelR7,0,6,1,1);
        gridpaneRequest.add(btRequest,0,7,1,1);
        gridpaneRequest.add(btRCancel,1,7,1,1);
        gridpaneRequest.add(OfficeNo,1,1,1,1);
        gridpaneRequest.add(currentBuildingNo,1,3,1,1);
        gridpaneRequest.add(currentRoomNo,1,2,1,1);
        gridpaneRequest.add(newBuildingNo,1,5,1,1);
        gridpaneRequest.add(newRoomNo,1,4,1,1);
        gridpaneRequest.add(movingDate,1,6,1,1);
        btRCancel.setOnAction(e->{
            rStage.close();
        });
//Need to add moving date
        btRequest.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("officeMove");
            Document newM = new Document("mRequestBy", passUsername).append("mOfficNo", OfficeNo.getText())
                    .append("mBuildingNo",newBuildingNo.getText()).append("mRoomNo",newRoomNo.getText()).append("cRoomNo",currentRoomNo.getText())
                    .append("cBuildingNo", currentBuildingNo.getText());
            collection.insertOne(newM);
            rStage.close();
        });

        //Request an office move Layout
        // VBox layoutRequest = new VBox(20);
        //layoutRequest.getChildren().addAll(labelR1);
        request = new Scene(gridpaneRequest,460,400);
        gridpaneRequest.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");

        rStage.setScene(request);
        rStage.setTitle("Office Move");
        rStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        rStage.setResizable(false);
        rStage.show();

    }





    public void changeDetails(){

        //Change Details Components
        Stage cStage = new Stage();
        GridPane gridpaneChange = new GridPane();
        Label labelC1 = new Label("Change Details");
        labelC1.setFont(Font.font(25));
        Label labelC2 = new Label("Office No");
        Label labelC3 = new Label("Current Office Room No");
        Label labelC4 = new Label("Current Building No");
        Label labelC5 = new Label("Current Moving Date");
        Label labelC6 = new Label("New Office Room No");
        Label labelC7 = new Label("New Building No");
        Label labelC8 = new Label("New Moving Date");
        Button btsearch = new Button("Search");
        Button btsave = new Button("Save Changes");
        Button btCCancel = new Button("Cancel");
        btCCancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btsave.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btsearch.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        currentMovingDate = new DatePicker();
        newMovingDate = new DatePicker();
        TextField officeNo = new TextField();
        TextField changeCurrentOfficeNo = new TextField();
        TextField changeCurrentBuildingNo = new TextField();
        TextField changeNewOfficeNo = new TextField();
        TextField changeNewBuildingNo = new TextField();
        gridpaneChange.setHgap(10);
        gridpaneChange.setVgap(10);
        gridpaneChange.setPadding(new Insets(40,10,10,10));
        gridpaneChange.add(labelC1,0,0,1,1);
        gridpaneChange.add(labelC2,0,1,1,1);
        gridpaneChange.add(labelC3,0,2,1,1);
        gridpaneChange.add(labelC4,0,3,1,1);
        gridpaneChange.add(labelC5,0,4,1,1);
        gridpaneChange.add(labelC6,0,5,1,1);
        gridpaneChange.add(labelC7,0,6,1,1);
        gridpaneChange.add(labelC8,0,7,1,1);
        gridpaneChange.add(officeNo,1,1,1,1);
        gridpaneChange.add(btsearch,2,2,1,1);
        gridpaneChange.add(btsave,1,8,1,1);
        gridpaneChange.add(btCCancel,2,8,1,1);
        gridpaneChange.add(changeCurrentBuildingNo,1,3,1,1);
        gridpaneChange.add(changeCurrentOfficeNo,1,2,1,1);
        gridpaneChange.add(currentMovingDate,1,4,1,1);
        gridpaneChange.add(changeNewOfficeNo,1,5,1,1);
        gridpaneChange.add(changeNewBuildingNo,1,6,1,1);
        btsearch.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("officeMove");
            Document document1 = collection.find(eq("mOfficNo",officeNo.getText())).first();
            changeCurrentBuildingNo.setText((String) document1.get("mBuildingNo"));
            changeCurrentOfficeNo.setText((String)document1.get("mRoomNo"));
        });

        btsave.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
             MongoCollection<Document> collection = database.getCollection("officeMove");
            Document document1 = collection.find(eq("mOfficNo",officeNo.getText())).first();
            System.out.println("before if");
            System.out.println(passUsername);
            System.out.println((String) document1.get("mRequestBy"));

            if(passUsername.equals((String)document1.get("mRequestBy"))){
          System.out.println("after if");
                collection.updateOne(eq("mOfficNo",officeNo.getText()),new Document("$set",new Document("mRoomNo",changeNewOfficeNo.getText())));
                collection.updateOne(eq("mOfficNo",officeNo.getText()),new Document("$set",new Document("mBuildingNo",changeNewBuildingNo.getText())));
                System.out.println("after update");
                cStage.close();
           }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Access Denied");
                alert.setHeaderText("Access Denied");
                alert.setContentText("Ooops, You have no authority to change the current record details. To change, please contact person who requested this move.");
                alert.showAndWait();

            }
        });
        btCCancel.setOnAction(e->{
            cStage.close();
        });


        //Change Details Layout
        //VBox layoutChange = new VBox(20);
        //layoutChange.getChildren().addAll(labelC1);
        change = new Scene(gridpaneChange,450,400);
        gridpaneChange.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");

        cStage.setScene(change);
        cStage.setTitle("Change Details");
        cStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        cStage.setResizable(false);
        cStage.show();
    }



    public void buildingPlan(){
        Stage bStage = new Stage();
        //Look Building Plans Components
        GridPane gridpanePlan = new GridPane();
        gridpanePlan.setHgap(10);
        gridpanePlan.setVgap(10);
        gridpanePlan.setPadding(new Insets(40,10,10,10));
        Label labelP1 = new Label("Building Plans");
        labelP1.setFont(Font.font(25));
        Label labelP2 = new Label("Building Number");
        Label labelP3 = new Label("Floor Number");
        TextField buildingNo = new TextField();
        TextField floorNo = new TextField();
        Button btView = new Button("View");
        Button btCancel = new Button("Cancel");
        btView.setOnAction(e->{
            HostServices hostServices = getHostServices() ;
            //hostServices.showDocument("C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png");
            String urlnew = new String("C:/Users/Vihanga/IdeaProjects/cw01/GUI/"+buildingNo.getText()+floorNo.getText()+".png");
            System.out.println(urlnew);
            hostServices.showDocument(urlnew);
        });
       /* btView.setOnAction(e->{
            try {
                MongoClient mongo = new MongoClient("localhost", 27017);

                DB db = mongo.getDB("furnitureItems");
                // --- Show Jasper Report on click-----
                new PrintReport().showReport();
            } catch (ClassNotFoundException | JRException | SQLException e1) {
                e1.printStackTrace();
            }
        });
*/

        btView.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btCancel.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        gridpanePlan.add(labelP2,0,0,1,1);
        gridpanePlan.add(labelP3,0,1,1,1);
        gridpanePlan.add(buildingNo,1,0,1,1);
        gridpanePlan.add(floorNo,1,1,1,1);
        gridpanePlan.add(btView,0,2);
        gridpanePlan.add(btCancel,1,2);






        //Look Building Plans Layout
        //VBox layoutPlan = new VBox(20);
        // layoutPlan.getChildren().addAll(labelP1);
        gridpanePlan.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");
        plan = new Scene(gridpanePlan,450,400);
        bStage.setScene(plan);
        bStage.setTitle("View Building Plans");
        bStage.setResizable(false);
        bStage.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        bStage.show();


    }


    public void securityQuestion(){

        //Account Settings - Change Sqcurity Question Components
        Stage squesStage  = new Stage();
        Label labelASQ2 = new Label("New Question");
        Label labelASQ3 = new Label("New Answer");
        Label labelASQ4 = new Label("Change Security Question");
        labelASQ4.setFont(Font.font(25));
        TextField assqNewQuestion = new TextField();
        TextField assqNewAnswer = new TextField();
        Button btASQSave = new Button("Save Changes");
        btASQSave.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 10 10 10;");
        btASQSave.setOnAction(e->{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("userAccount");
            collection.updateOne(eq("username", passUsername), new Document("$set", new Document("securityQ", assqNewQuestion.getText())));
            collection.updateOne(eq("username", passUsername), new Document("$set", new Document("answer", assqNewAnswer.getText())));
            squesStage.close();
        });
//Change Account Settings SQ
        VBox layoutASSQ = new VBox(10);
        layoutASSQ.setPadding(new Insets(40,10,10,10));
        layoutASSQ.getChildren().addAll(labelASQ4,labelASQ2,assqNewQuestion,labelASQ3,assqNewAnswer,btASQSave);
        aSettingsSQ = new Scene(layoutASSQ,450,400);
        layoutASSQ.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/defaultBG.png\")");
        squesStage.setScene(aSettingsSQ);
        squesStage.setTitle("Security Question");
        squesStage.setResizable(false);
        squesStage.show();

    }






    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Stage childWindow = new Stage();
        //Login components
       // Label labelL1 = new Label ("Welcome to UGNiP FMIS");
        final TextField txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        PasswordField pswPassword = new PasswordField();
        pswPassword.setPromptText("Password");
        String sUsername = txtUsername.getText();
        String sPassword = pswPassword.getText();
        Button btLogin = new Button("Login");
        btLogin.setStyle("-fx-background-color:#e8ebef; -fx-padding: 10 30 10 30;");
        btLogin.setOnAction((event) ->
        {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            //Access the database
            MongoDatabase database = mongoClient.getDatabase("uogdb");
            MongoCollection<Document> collection = database.getCollection("userAccount")    ;
            Document document1 = collection.find(eq("username",txtUsername.getText())).first();
            if(txtUsername.getText().equals("admin") && pswPassword.getText().equals("123")){
                dashoboardMethod();
            }
            else
                if(txtUsername.getText().equals((String) document1.get("username"))){
                    if(pswPassword.getText().equals((String) document1.get("password"))) {
                    System.out.println("Hello!");                    System.out.println(txtUsername.getText());
                    passUsername = txtUsername.getText();
                    if(document1.get("permissionType").equals("High")){
                    dashoboardMethod();}
                    else if(document1.get("permissionType").equals("Low")){
                        dashoboardMethodLow();
                    }
                    else{
                        dashoboardMethodMedium();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Password");
                    alert.setHeaderText("Invalid Password!");
                    alert.setContentText("Ooops, the password you entered for user: "+txtUsername.getText()+" is invalid! Please Try again.");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid User");
                alert.setHeaderText("Invalid User!");
                alert.setContentText("Ooops, the user is not found! Please Try again.");
                alert.showAndWait();
            }
        });

        //Login layout
        VBox layoutLogin = new VBox(20);
        layoutLogin.setPadding(new Insets(80,20,20,20));
        Image loginImage = new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/login.png");
        ImageView loginView = new ImageView(loginImage);
        layoutLogin.getChildren().addAll(txtUsername,pswPassword,btLogin);
        login = new Scene(layoutLogin, 395,211);
        layoutLogin.setStyle("-fx-background-image: url(\"file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/login.png\")");
        //Display login first
        window.setScene(login);
        /*BackgroundImage bgImageLogin = new BackgroundImage(loginImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background loginBackgroud = new Background(bgImageLogin); */
       // ImagePattern pattern = new ImagePattern(loginImage);
       // login.setFill(pattern);



        window.setTitle("Login");
        window.getIcons().add(new Image("file:///C:/Users/Vihanga/IdeaProjects/cw01/GUI/logo.png"));
        window.setResizable(false);
        window.show();
    }



    public void mongoDbConnect(String collectionName){
        mongoClient = new MongoClient( "localhost" , 27017 );
        database = mongoClient.getDatabase("uogdb");
        collection = database.getCollection(collectionName);
    }


    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        //Access the database
        MongoDatabase database = mongoClient.getDatabase("uogdb");
        MongoCollection<Document> collection = database.getCollection("testData");
        System.out.println("Collection myCollection selected successfully");

        List<Document> documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());

        for(Document document : documents){
            System.out.println(document);
        }
        launch(args);




    }
}
