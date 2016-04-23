package scotip.web.pages.logged;

import org.hibernate.Session;
import scotip.dao.LineDAO;
import scotip.dao.SwitchboardDAO;
import scotip.entities.Company;
import scotip.entities.Line;
import scotip.entities.Switchboard;
import scotip.util.SessionUtil;
import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "SwitchboardNew", urlPatterns = "/u/switch/new")
public class SwitchboardNew extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "switchboard";
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);

        // save values for reuse
        HashMap<String, Object> previousPOST = new HashMap<>();

        // init an empty error message
        String formErrorPageContent = "";

        // check security token
        String securityToken = req.getParameter("securityToken");
        if (securityToken == null || !SessionUtil.isSecurityTokenValid(req.getSession(),securityToken)) {
            formErrorPageContent += "Security token is outdated. Please resend the form." + "<br/>\n";
        }



        // check name
        String name = req.getParameter("name");
        previousPOST.put("name", name);
        if (name == null || name.length() < 4 || name.length() > 30) {
            formErrorPageContent += "The service name should have between 4 and 30 characters." + "<br/>\n";
        }

        // check description
        String description = req.getParameter("description");
        previousPOST.put("description", description);
        if (description == null || description.length() < 20 || description.length() > 1000) {
            formErrorPageContent += "The description should have 20 characters at least." + "<br/>\n";
        }


        /**
         * Common parameters
         */
        // accessCode and Shared line
        int accessCode = -1, sharedLine = -1;
        Line chosenLine = null;
        LineDAO lineDAO = new LineDAO();

        // Check if it is a shared number or not
        String typeNumber = req.getParameter("typeNumber");
        previousPOST.put("typeNumber", typeNumber);
        if (typeNumber != null && typeNumber.equals("shared")) {

            try {
                sharedLine = Integer.parseInt(req.getParameter("sharedLine"));
            } catch (NumberFormatException nfe) {
            }
            previousPOST.put("sharedLine", sharedLine);
            if (sharedLine < 0) {
                formErrorPageContent += "This shared number is invalid." + "<br/>\n";
            } else {
                /**
                 * Check the numero is existing
                 */
                chosenLine = lineDAO.getSharedNumber(sharedLine);

                if(chosenLine==null){
                    formErrorPageContent += "The shared line is invalid." + "<br/>\n";
                }

                /**
                 * @TODO Have to check a line with the same accessCode
                 */
            }


            try {
                accessCode = Integer.parseInt(req.getParameter("accessCode"));
            } catch (NumberFormatException nfe) {
            }
            previousPOST.put("accessCode", accessCode);

            if (accessCode < 50 || accessCode > 999) {
                formErrorPageContent += "Please choose an access code between <strong>50</strong> and <strong>999</strong>." + "<br/>\n";
            } else {
                /**
                 * @TODO HAVE TO CHECK IT's AVAILABILITY!
                 */


            }

        } else if (typeNumber != null && typeNumber.equals("dedicated")) {
            formErrorPageContent += "This access mode is not supported at this time." + "<br/>\n";

        } else {
            formErrorPageContent += "The access mode is incorrect." + "<br/>\n";
        }


        /**
         * No error, perform some save.
         */
        if (formErrorPageContent.length() == 0) {
            Switchboard newSwitchboard = new Switchboard();
            newSwitchboard.setName(name);
            newSwitchboard.setDescription(description);
            newSwitchboard.setPhoneCodeAccess(accessCode);
            newSwitchboard.setEnabled(true);

            // set switchboard company
            Company company = new Company();
            company.setId(7);

            // switchboard id
            newSwitchboard.setCompany(company);

            // save
            SwitchboardDAO switchboardDAO = new SwitchboardDAO();
            switchboardDAO.save(newSwitchboard);

            // okkk
            HashMap<String, Object> model = new HashMap<>();
            model.put("switchboard", newSwitchboard);
            render("pages/switchboard/creationDone.twig", req, resp, model);

        } else {

            // render
            HashMap<String, Object> model = new HashMap<>();
            model.put("formErrorPageContent", formErrorPageContent);
            model.put("POST", previousPOST);
            printPage(req, resp, model);

        }
    }


    /**
     * No data provided.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
        printPage(req, resp, new HashMap<>());
    }


    /**
     * Print the page
     *
     * @param req
     * @param resp
     * @param modelView
     */
    protected void printPage(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> modelView) {
        // get shared numbers
        LineDAO lineDAO = new LineDAO();
        List<Line> sharedLines = lineDAO.getSharedLines();


        // Asks for a security token
        String securityToken = SessionUtil.createSecurityToken(req.getSession());
        modelView.put("securityToken",securityToken);

        // transmits data
        modelView.put("sharedNumbers", sharedLines);

        // render
        render("pages/switchboard/new.twig", req, resp, modelView);
    }
}
