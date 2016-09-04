package org.amc.servlet

import org.amc.model.MouldingProcess
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.springframework.stereotype.Controller;

@Controller
class MouldingProcessSaveController extends GenericSaveController<MouldingProcess, MouldingProcessSearch> {

}
