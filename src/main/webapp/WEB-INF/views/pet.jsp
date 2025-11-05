<%--
    Repository나 POST 관련된 처리 등을 Controller로 로직을 옮기고,
    View(UI) jsp는 레이아웃이나 디자인 자체에 집중 -> MVC2
--%>
<%--<%@ page import="org.example.mvc.model.repository.InMemoryPetRepository" %>--%>
<%@ page import="org.example.mvcprojcet.mvc.model.entity.Pet" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- JSP가 만들어질 때 1번만 선언되게 된다 -> 내부에 있는 petList는 유지 --%>
<%--<%! PetRepository repository = new PetRepository(); %>--%>
<html>
<head>
    <title>반려동물을 소개합니다</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        h3 {
            color: #333;
            margin-bottom: 15px;
        }

        form {
            background-color: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 400px;
            margin-bottom: 30px;
        }

        form input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        form input:focus {
            outline: none;
            border-color: #4CAF50;
        }

        form button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }

        form button:hover {
            background-color: #45a049;
        }

        .pet-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .pet-card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            min-width: 200px;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .pet-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        .pet-card strong {
            color: #555;
            font-size: 12px;
            text-transform: uppercase;
        }

        .pet-card .pet-name {
            font-size: 20px;
            font-weight: bold;
            color: #333;
            margin: 8px 0;
        }

        .pet-card .pet-info {
            color: #666;
            margin: 5px 0;
            font-size: 14px;
        }
    </style>
</head>
<body>
<%-- post 시 처리하는 로직 --%>
<%--    <%--%>
<%--        if (request.getMethod().equals("POST")) {--%>
<%--            System.out.println("POST 요청");--%>
<%--            // form에서 input으로 넣은 걸 가지고 있음--%>
<%--            System.out.println(request.getParameter("name"));--%>
<%--            System.out.println(request.getParameter("age"));--%>
<%--            System.out.println(request.getParameter("category"));--%>
<%--            String name = request.getParameter("name");--%>
<%--            int age = Integer.parseInt(request.getParameter("age")); // String -> int (변환)--%>
<%--            String category = request.getParameter("category");--%>
<%--            Pet pet = new Pet(name, age, category);--%>
<%--            repository.save(pet);--%>
<%--        }--%>
<%--    %>--%>

<%-- form --%>
<form method="post">
    <h3>새로운 반려동물</h3>
    이름 : <input type="text" name="name"><br>
    나이 : <input type="number" name="age"><br>
    종류 : <input type="text" name="category"><br>
    <button>등록</button>
</form>

<%-- view -> model -> pet -> view --%>
<%--    <% List<Pet> petList = repository.findAll(); %>--%>
<div class="pet-container">
    <%--        <% for (Pet pet : petList) { %>--%>
    <% for (Pet pet : (List<Pet>) request.getAttribute("petList")) { %>
    <div class="pet-card">
        <div class="pet-name"><%= pet.name() %></div>
        <div class="pet-info"><strong>나이:</strong> <%= pet.age() %>살</div>
        <div class="pet-info"><strong>종류:</strong> <%= pet.category() %></div>
    </div>
    <% } %>
</div>
</body>
</html>