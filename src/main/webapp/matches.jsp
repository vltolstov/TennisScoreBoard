<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Итоги матчей</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <div class="page-center-wrapper">
        <div class="page-center">
            <p class="page-header">Итоги матчей</p>
            <div class="main-block">
                <div class="player-filter-form-wrapper">
                    <form class="filter-form" method="POST" action="matches">
                        <label for="search-player-name">Поиск по имени игрока:</label>
                        <input type="text" name="search-player-name" id="search-player-name" placeholder="Введите имя">
                        <button class="search-button" type="submit">Найти</button>
                    </form>
                </div>
                <div class="scores">
                    <table class="scores-table">
                        <tr>
                            <th>№ Матча</th>
                            <th>Игрок №1</th>
                            <th>Игрок №2</th>
                            <th>Счет</th>
                            <th>Победитель</th>
                        </tr>
                        <c:forEach var="match" items="${matches}">
                            <tr>
                                <td>${match.id}</td>
                                <td>${match.firstPlayer.name}</td>
                                <td>${match.secondPlayer.name}</td>
                                <td>${match.score.setsScore}</td>
                                <td>${match.winner.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <nav>
                    <div class="pagination">
                        <a href="#">Назад</a>
                        <span>1</span>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">Вперед</a>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <div class="go-home">
        <a href="${pageContext.request.contextPath}"><img src="images/home.svg"></a>
    </div>
</body>
</html>