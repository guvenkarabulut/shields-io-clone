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
    public String getShield(@PathVariable String name,@PathVariable String baseColor) {
        return generateShieldSvg(name, baseColor);
    }

    private String generateShieldSvg(String name, String baseColor) {
        String textColor = isDarkColor(baseColor);
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"120\" height=\"28\"> role=\"img\" aria-label=\""+name+"\"" +
                "<g shape-rendering=\"crispEdges\">"+
                    "<rect width=\"32\" height=\"28\" fill=\"#e6e6e6\"/>"+
                    "<rect x=\"32\" width=\"77.5\" height=\"28\" fill=\"#"+baseColor+"\"/>"+
                "</g>"+
                "    <g fill=\"#fff\" text-anchor=\"middle\" font-family=\"Verdana,Geneva,DejaVu Sans,sans-serif\" text-rendering=\"geometricPrecision\" font-size=\"100\">" +
                "        <image x=\"9\" y=\"7\" width=\"14\" height=\"14\" xlink:href=\"data:image/svg+xml;base64,PHN2ZyBmaWxsPSJ3aGl0ZSIgcm9sZT0iaW1nIiB2aWV3Qm94PSIwIDAgMjQgMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PHRpdGxlPjREPC90aXRsZT48cGF0aCBkPSJNMjAuNjQgMHYyNEgzLjM2VjBoMTcuMjh6TTEwLjQ5IDExLjgyN2MtLjExNS4xMzgtNS44ODIgNi43ODktNS45ODMgNi45LS4wNTguMDctLjE4Ny4xOTQtLjE4Ny4zNiAwIC4xNTMuMTg3LjIwOC4zNi4yMDhoNC40di0xLjA2N0g1LjgzYy40OS0uNjEgMy4zOC0zLjgyNCAzLjY5Ni00LjIyNnY1LjM0YzAgLjE5NC0uMDA1Ljk2NS0uMDQzIDEuNjAyLS4wMjkuNDMtLjEzLjYzNy0uNjYxLjY5My0uMjMuMDI3LS41MzMuMDQxLS42NjIuMDQxLS4wNzIgMC0uMTE1LjA4My0uMTE1LjE4IDAgLjA5Ny4wNzIuMTY3LjIzLjE2Ny43NzcgMCAxLjUzOS0uMDQyIDEuOTQyLS4wNDIgMS4yMzYgMCAyLjY0Ni4wOTcgMy4xNzguMDk3IDIuNjE4IDAgNC4wOTktLjk3IDQuNzQ2LTEuNjA3Ljc5MS0uNzc2IDEuNTM5LTIuMDkzIDEuNTM5LTMuODEgMC0xLjYyMi0uNjYyLTIuNzU4LTEuMzgtMy40NjUtMS41NC0xLjU2NS0zLjkxMy0xLjU2NS01LjY4Mi0xLjU2NS0uNTYgMC0xLjAzNS4wMjctMS4wNjQuMDI3LS4zODguMDQyLS4zNDUtLjEyNC0uNTktLjEzOC0uMTU4LS4wMTQtLjI1OC4wNTUtLjQ3NC4zMDV6bTEuODk4LjQ0M2MxLjEwOCAwIDIuNzE5LjE2NiA0LjAyNyAxLjM3Mi42MDQuNTU0IDEuMzY3IDEuNjc2IDEuMzY3IDMuNDA4IDAgMS40MTQtLjI4OCAyLjY2LTEuMTk0IDMuNDA5LS44NDkuNzA2LTEuODEyLjk4NC0zLjI2NS45ODQtMS4xMjIgMC0xLjY4My0uMjkxLTEuODctLjU0LS4xMTUtLjE1My0uMTcyLS42OTQtLjE4Ni0xLjA0IDAtLjA5Ny0uMDE1LS4yOS0uMDE1LS41NjhoMS4wMjFjLjI0NSAwIC4zMTctLjA1NS4zODktLjE4LjEtLjE4LjI0NC0uNzM1LjI0NC0uODYgMC0uMTEtLjA1Ny0uMTY2LS4xMy0uMTY2LS4wODYgMC0uMjczLjEzOS0uNjQ3LjEzOWgtLjg3N3YtNS41ODRjMC0uMTUyLjA1OC0uMjIyLjE3My0uMjc3LjExNS0uMDU2LjY3Ni0uMDk3Ljk2My0uMDk3eiIvPjwvc3ZnPg==\"/>" +
                "        <text transform=\"scale(.1)\" x=\"707.5\" y=\"175\" textLength=\"535\" fill=\"#"+textColor+"\" font-weight=\"bold\">"+name+"</text>" +
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


