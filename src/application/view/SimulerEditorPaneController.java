package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import application.DailyBankState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.lang.Math;


public class SimulerEditorPaneController implements Initializable {

	// Etat application
	@SuppressWarnings("unused")
	private DailyBankState dbs;

	// Fenêtre physique
	private Stage primaryStage;

	// Données de la fenêtre

	// Manipulation de la fenêtre
	public void initContext(Stage _primaryStage, DailyBankState _dbstate) {
		this.primaryStage = _primaryStage;
		this.dbs = _dbstate;
		this.configure();
	}

	private void configure() {
		this.primaryStage.setOnCloseRequest(e -> this.closeWindow(e));
	}

	public void displayDialog() {
		this.butOk.setText("Simulation emprunt");
		this.butCancel.setText("Annuler");
		this.primaryStage.showAndWait();
	}



	// Gestion du stage
	private Object closeWindow(WindowEvent e) {
		this.doCancel();
		e.consume();
		return null;
	}

	// Attributs de la scene + actions
	@FXML
	private TextField montant;

	@FXML
	private TextField annee;

	@FXML
	private TextField TA;

	@FXML
	private TextArea txt;

	@FXML
	private TextField montantAss;

	@FXML
	private TextField TauxAnnuel;

	@FXML
	private TextField DureeMois;




	@FXML
	private Button butOk;
	@FXML
	private Button butCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	private void doCancel() {
		this.primaryStage.close();
	}

	@FXML
	private void doSimul() {

		String aff = "";

		if (!montant.getText().isEmpty() && !annee.getText().isEmpty() && !TA.getText().isEmpty()) {

			int numTA= Integer.parseInt(TA.getText());
			int numA= Integer.parseInt(annee.getText());
			int numMontant= Integer.parseInt(montant.getText());

			aff ="Année | Capital restant dû |Intérêts | Amortissement du capital | Annuité\n";

			int Capital=numMontant;
			int interet=(Capital/100)*numTA;
			int amor= (Capital/numA);
			int annuite= amor+interet;


			int totI = 0;
			int totC = 0;
			int totA = 0;
			for (int i =0 ; i<numA; i++) {
				int bona =i+1;
				totI += interet;
				totC += amor;
				totA += annuite;
				aff = aff + "    "+ bona +"             |   " + Capital + "    |     " + interet + "     |               " + amor +"                  |   " + annuite + "\n";

				Capital = Capital-amor;
				interet = (Capital/100)*numTA;
				annuite = amor+interet;


			}


			aff = aff + "    "+ " Total " +"   |   " + "       " + "    |     " + totI + "     |               " + totC +"                  |   " + totA + "\n";
			txt.setText(aff);



		}

	}





	@FXML
	private void doAss() {

		String aff = "";

		if (!montantAss.getText().isEmpty() && !TauxAnnuel.getText().isEmpty() && !DureeMois.getText().isEmpty()) {

			float numTA= Float.parseFloat(TauxAnnuel.getText());
			float numA= Integer.parseInt(DureeMois.getText());
			double numMontant= Integer.parseInt(montantAss.getText());
			float Tapl = numTA/100/12;
			float tour = numA;
			numA = numA - numA - numA;

			aff ="Num mois | Capital restant dû en début de période |Intérêts | Montant des interet | Montant du princiapl  |  Montant à rembourser (Mensualité) | Capital restant du en fin de période \n";

			double CapDeb=numMontant;
			double interet=CapDeb*Tapl;

			double MontantArembourser = numMontant * (Tapl/ (1-(Math.pow(1+Tapl, numA))));


			double princ= MontantArembourser-interet;

			double CapFin= CapDeb-princ;


			for (int i =0 ; i<tour; i++) {
				int bona =i+1;

				aff = aff + "    "+ bona +"             |                   " + CapDeb + "             |                           " + interet + "     |               " + princ +"                  |   " + MontantArembourser + "  |    " + CapFin + "\n";






				interet = CapDeb*Tapl;
				CapFin = CapDeb -princ;
				princ = MontantArembourser-interet;
				CapDeb = CapFin;


			}


			txt.setText(aff);





		}


	}








}
