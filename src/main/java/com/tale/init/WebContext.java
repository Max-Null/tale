package com.tale.init;

import com.blade.Blade;
import com.blade.config.BConfig;
import com.blade.context.WebContextListener;
import com.blade.ioc.BeanProcessor;
import com.blade.ioc.Ioc;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.view.ViewSettings;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import com.tale.ext.AdminCommons;
import com.tale.ext.Commons;
import com.tale.ext.JetTag;
import com.tale.ext.Theme;
import com.tale.service.OptionsService;
import com.tale.service.SiteService;
import jetbrick.template.JetGlobalContext;
import jetbrick.template.resolver.GlobalResolver;

import javax.servlet.ServletContext;

/**
 * Tale初始化进程
 *
 * @author biezhi
 */
public class WebContext implements BeanProcessor, WebContextListener {

    @Inject
    private OptionsService optionsService;

    private static boolean dbIsOk = false;

    @Override
    public void init(BConfig bConfig, ServletContext sec) {
        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
        templateEngine.addConfig("jetx.import.macros", "/comm/macros.html");
        GlobalResolver resolver = templateEngine.getGlobalResolver();
        resolver.registerFunctions(Commons.class);
        resolver.registerFunctions(Theme.class);
        resolver.registerFunctions(AdminCommons.class);
        resolver.registerTags(JetTag.class);

        JetGlobalContext context = templateEngine.getGlobalContext();
        context.set("version", bConfig.config().get("app.version", "v1.0"));

        ViewSettings.$().templateEngine(templateEngine);
        if (dbIsOk) {
            TaleConst.OPTIONS.addAll(optionsService.getOptions());
            TaleConst.INSTALL = true;
            Commons.setSiteService(Blade.$().ioc().getBean(SiteService.class));
        }
        TaleConst.BCONF = bConfig.config();
    }

    @Override
    public void register(Ioc ioc) {
        dbIsOk = TaleJdbc.injection(ioc);
    }

}