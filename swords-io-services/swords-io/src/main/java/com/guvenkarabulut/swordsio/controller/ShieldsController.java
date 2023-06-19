package com.guvenkarabulut.swordsio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShieldsController {
    final RestTemplate restTemplate;
    @GetMapping("/{name}-{baseColor}.svg")
    public String getShield(
            @PathVariable String name,
            @PathVariable String baseColor,
            @RequestParam String logo,
            @RequestParam String logoColor,
            @RequestParam String logoBgColor
    ) {
       String b64Logo=restTemplate.getForObject("http://127.0.0.1:5000/"+logo+"/"+logoColor,String.class);
        return generateShieldSvg(name, baseColor,b64Logo,logoBgColor);
    }

    private String generateShieldSvg(String name, String baseColor,String logo,String logoBgColor) {
        String textColor = isDarkColor(baseColor);
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\""+name.length()*10.5+"\" height=\"28\"> role=\"img\" aria-label=\""+name+"\"" +
                "<g shape-rendering=\"crispEdges\">"+
                    "<rect width=\"32\" height=\"28\" fill=\"#"+logoBgColor+"\"/>"+
                    "<rect x=\"32\" width=\""+name.length()*10.5+"\" height=\"28\" fill=\"#"+baseColor+"\"/>"+
                "</g>"+
                "    <g fill=\"#fff\" text-anchor=\"middle\" font-family=\"Verdana,Geneva,DejaVu Sans,sans-serif\" text-rendering=\"geometricPrecision\" font-size=\"100\">" +
                "        <image x=\"9\" y=\"7\" width=\"14\" height=\"14\" xlink:href=\"data:image/svg+xml;base64,"+logo+"\"/>" +
                "        <text transform=\"scale(.1)\" x=\""+name.length()*62.5+"\" y=\"175\" textLength=\""+name.length()*75+"\" fill=\"#"+textColor+"\" font-weight=\"bold\">"+name+"</text>" +
                "    </g>"+
                "</svg>";
    }
    public static String isDarkColor(String hex) {
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        double colorValue = (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255;
        if (colorValue > 0.5) {
            return "333"; // açık renk
        } else {
            return "fff"; // koyu renk
        }
    }
}


