package com.magistr.duck.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermInfo;
import com.magistr.duck.service.TermService;

@Controller
public class TermController {

    private static Logger logger = LoggerFactory.getLogger(TermController.class);

    @Autowired
    private TermService termService;

    @ModelAttribute
    public void addTermModel(Model model) {
        Term term = new Term();
        Term translation = new Term();
        TermInfo termInfo = new TermInfo();
        TermInfo translationInfo = new TermInfo();

        TermInfo.TermCommonInfo commonInfo = termInfo.new TermCommonInfo();
        termInfo.setCommonInfo(commonInfo);
        translationInfo.setCommonInfo(commonInfo);

        termInfo.setExamples(new ArrayList<String>(1));
        translationInfo.setExamples(new ArrayList<String>(1));

        translation.setTermInfo(translationInfo);
        translation.setTranslation(term);

        term.setTermInfo(termInfo);
        term.setTranslation(translation);

        term.setLang(Lang.RU);
        translation.setLang(Lang.DE);

        model.addAttribute("term", term);
    }

    @ModelAttribute("terms")
    public List<Term> addTermsModel() {
        return termService.getTerms(Lang.DE);
    }

    @RequestMapping("/term.html")
    public String term() {
        return "term/term";
    }

    @RequestMapping("/term/edit.html")
    public String editTerm() {
        return "term/edit";
    }

    @RequestMapping(path = "/term/edit/save", method = RequestMethod.POST)
    public String editTerm(@ModelAttribute("term") Term term) {
        if (term.getName() != null && term.getTranslation().getName() != null) {// validation
            termService.saveTerm(term);
        }
        return "redirect:/edit.html";
    }

    @ResponseBody
    @RequestMapping(path = "/term/search", method = RequestMethod.POST)
    public Map<String, String> searchTerm(@RequestBody Search search) {
        Map<String, String> map = new HashMap<>(2);
        if (search.getDeTerm() != null && !search.getDeTerm().isEmpty()) {
            Term t = termService.getTerm(search.getDeTerm(), Lang.DE);
            map.put(t.getName(), t.getTranslation().getName());
        }
        if (search.getRuTerm() != null && !search.getRuTerm().isEmpty()) {
            Term t = termService.getTerm(search.getRuTerm(), Lang.RU);
            map.put(t.getTranslation().getName(), t.getName());
        }
        return map;
    }

    private static class Search {

        private String deTerm;
        private String ruTerm;

        public Search() {

        }

        public void setDeTerm(String deTerm) {
            this.deTerm = deTerm;
        }

        public void setRuTerm(String ruTerm) {
            this.ruTerm = ruTerm;
        }

        public String getDeTerm() {
            return deTerm;
        }

        public String getRuTerm() {
            return ruTerm;
        }
    }
}
