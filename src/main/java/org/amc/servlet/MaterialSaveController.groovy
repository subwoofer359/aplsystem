package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SaveAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Locale;

import javax.annotation.Resource
import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.validation.Valid;

@Controller
class MaterialSaveController extends GenericSaveController<Material, MaterialSearch> {
    
    private static final Logger logger = Logger.getLogger(MaterialSaveController);
    
    static final String ITEM_VIEW = 'Material';
    static final String REDIRECT_SEARCH = 'redirect:Material_search';
    
    public void init() {
        setView(GenericSaveController.View.ITEM_VIEW, ITEM_VIEW);
        setView(GenericSaveController.View.REDIRECT_SEARCH, REDIRECT_SEARCH)
    }
    
    @InitBinder("material")
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new MyFloatFormatter(),
             'density',
             'linear_expansion',
             'water_absorption',
             'material_drying',
             'melting_temp_lower',
             'melting_temp_upper',
             'mould_shrinkage',
             'mould_temp_low',
             'mould_temp_upper'
             );
         
         binder.addCustomFormatter(new MyIdFormatter(), 'id');
    }

    @RequestMapping(method=RequestMethod.POST, value='/Material_save', params='mode=Enter')
    ModelAndView saveMaterial(@Valid @ModelAttribute Material material, BindingResult errors) {
       return super.save(material, errors);
    }

    @RequestMapping(method=RequestMethod.POST, value='/Material_save', params='mode=Edit')
    ModelAndView updateMaterial(@Valid @ModelAttribute Material material, BindingResult errors) {
        return super.update(material, errors);
    }
    
    public void setMaterialActionFactory(ActionFactory<Material, MaterialSearch> materialActionFactory) {
        this.actionFactory = materialActionFactory;
    }
    
    static class MyFloatFormatter implements org.springframework.format.Formatter<Float> {

        @Override
        public Float parse(String text, Locale locale) throws ParseException {
            if('' == text) {
                return 0.0f;
            } else {
                return Float.parseFloat(text);
            }
        }

        @Override
        public String print(Float object, Locale locale) {
            return String.valueOf(object);
        }
        
    } 
    
    static class MyIdFormatter implements org.springframework.format.Formatter<Integer> {
        
                @Override
                public Integer parse(String text, Locale locale) throws ParseException {
                    if('' == text) {
                        return 0;
                    } else {
                        return Integer.parseInt(text);
                    }
                }
        
                @Override
                public String print(Integer object, Locale locale) {
                    return String.valueOf(object);
                }
                
    }
}
