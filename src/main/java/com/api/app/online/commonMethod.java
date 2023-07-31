package com.api.app.online;

import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class commonMethod { // 함수 모음

    /* HTML 변환 함수 */
    public String getHtmlReplace(String srcString, String name) {
        if (name.equals("esc")) {
            // HTRL -> DB
            return HtmlUtils.htmlEscape(srcString);
        } else if (name.equals("uesc")) {
            // DB -> HTML
            return HtmlUtils.htmlUnescape(srcString);
        }
        return "";
    }

}
