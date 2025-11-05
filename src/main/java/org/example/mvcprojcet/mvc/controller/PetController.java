package org.example.mvcprojcet.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mvcprojcet.mvc.sercive.PetService;
import org.example.mvcprojcet.mvc.sercive.PetServiceImpl;

import java.io.IOException;

@WebServlet("/pet") // pet이라는 경로에 대응
public class PetController extends HttpServlet {
    //    private final InMemoryPetRepository petRepository = new InMemoryPetRepository();
//    private final PetRepository petRepository = new InMemoryPetRepository();
    private final PetService petService = new PetServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // MVC2
//        req.setAttribute("petList", petRepository.findAll()); // 데이터를 변경하거나 처리...
        req.setAttribute("petList", petService.readAll());
        // forward -> 주소를 유지시키면서 실제 접속하는 경로를 바꾸는 기술
        req.getRequestDispatcher("/WEB-INF/views/pet.jsp").forward(req, resp);
        // MVC1 -> Controller -> 접속 역할
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // MVC2
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String category = req.getParameter("category");
//        Pet pet = new Pet(name, age, category);
//        petRepository.save(pet);
        petService.create(name, age, category);
        // PRG
        // POST -> Refresh -> POST를 다시 보내게 됨
        // POST -> Redirect (새로운 주소로 GET으로 보내는 기법) -> GET
        resp.sendRedirect(req.getContextPath() + "/pet");

        // forward -> 주소를 유지시키면서 실제 접속하는 경로를 바꾸는 기술
        // req.getRequestDispatcher("/WEB-INF/views/pet.jsp").forward(req, resp);
        // MVC1 -> Controller -> 접속 역할
    }
}