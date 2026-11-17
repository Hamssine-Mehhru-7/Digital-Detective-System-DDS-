
package com.digitaldetective;

import com.digitaldetective.database.CaseDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CaseController {

    @GetMapping("/cases")
    public String viewCases(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        try {
            List<String> cases = CaseDAO.getCasesByUserId(userId);
            model.addAttribute("cases", cases);
        } catch (SQLException e) {
            model.addAttribute("error", "Unable to load cases.");
            e.printStackTrace();
        }

        return "cases";
    }

    @GetMapping("/cases/new")
    public String newCasePage(HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        return "create-case";
    }

    
    
    @PostMapping("/cases/create")
    public String createCase(
            @RequestParam String title,
            @RequestParam String description,
            HttpSession session,
            Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        if (title.isBlank() || description.isBlank()) {
            model.addAttribute("error", "Please fill in all fields.");
            return "create-case";
        }

        try {
            int caseId = CaseDAO.createCaseAndGetId(
                    userId, title.trim(), description.trim());

            return "redirect:/analysis-workspace?caseId=" + caseId;

        } catch (SQLException e) {
            model.addAttribute("error", "Unable to create investigation case.");
            e.printStackTrace();
            return "create-case";
        }
    }

@GetMapping("/new-investigation")
public String newInvestigation(
        @RequestParam int caseId,
        HttpSession session,
        Model model) throws SQLException {

    Integer userId = (Integer) session.getAttribute("userId");

    if (userId == null) {
        return "redirect:/login";
    }

    if (!CaseDAO.userOwnsCase(caseId, userId)) {
        return "redirect:/cases";
    }

    return "redirect:/analysis-workspace?caseId=" + caseId;
}


}

