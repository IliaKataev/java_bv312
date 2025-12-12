package com.laptop.servlet;

import com.laptop.beans.Manufacturer;
import com.laptop.dao.ManufacturerDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ManufacturerServlet", urlPatterns = {"/", "/manufacturers", "/manufacturer"})
public class ManufacturerServlet extends HttpServlet {
    private ManufacturerDAO manufacturerDAO;

    @Override
    public void init() throws ServletException{
        manufacturerDAO = ManufacturerDAO.getInstance();
    }

    //doGet

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        try{
            if("/manufacturer".equals(path) && idParam != null){
                showManufacturerDetails(request, response, Integer.parseInt(idParam));
            } else if ("search".equals(action)){
                searchManufacturers(request, response);
            } else if ("country".equals(action)){
                filterByCountry(request, response);
            } else{
                showAllManufacturers(request, response);
            }
        } catch(NumberFormatException e){
            request.setAttribute("error", "Неверный формат ID");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch(Exception e){
            request.setAttribute("error", "Произошла ошибка - " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }




    private void showManufacturerDetails(HttpServletRequest request, HttpServletResponse response, int i) throws ServletException, IOException {
        Optional<Manufacturer> manufacturerOpt = manufacturerDAO.getManufacturerById(i);

        if(manufacturerOpt.isPresent()){
            Manufacturer manufacturer = manufacturerOpt.get();
            request.setAttribute("manufacture", manufacturer);

            List<Manufacturer> similarManufacturer = manufacturerDAO.getManufacturerByCountry(manufacturer.getCountry())
                    .stream()
                    .filter(m -> m.getId() != manufacturer.getId())
                    .limit(3)
                    .toList();
            request.setAttribute("similarManufacturers", similarManufacturer);

            request.getRequestDispatcher("manufacturer.jsp").forward(request, response);
        }   else{
            request.setAttribute("error", "Производитель c ID " + i + "не найден" );
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void searchManufacturers(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
        String query = request.getParameter("query");
        if(query == null || query.trim().isEmpty()){
            showAllManufacturers(request,response);
            return;
        }

        List<Manufacturer> searchResult = manufacturerDAO.searchManufacturers(query);
        request.setAttribute("manufacturers", searchResult);
        request.setAttribute("searchQuery", query);
        request.setAttribute("resultCount", searchResult.size());
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    private void filterByCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String country = request.getParameter("country");
        if(country == null || country.trim().isEmpty()){
            showAllManufacturers(request, response);
            return;
        }

        List<Manufacturer> filteredManufacturers = manufacturerDAO.getManufacturerByCountry(country);
        request.setAttribute("manufacturers", filteredManufacturers);
        request.setAttribute("filteredCounty", country);
        request.setAttribute("resultCount", filteredManufacturers.size());
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    private void showAllManufacturers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Manufacturer> manufacturerList = manufacturerDAO.getAllManufacturers();
        request.setAttribute("manufacturers", manufacturerList);
        request.setAttribute("totalCount", manufacturerDAO.getManufacturerCount());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        doGet(request, response);
    }
}
