<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Матч: Игрок1 vs Игрок2</title>
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
            <p class="page-header">Матч: Игрок1 vs Игрок2</p>
            <div class="main-block">
                <div class="score">
                    <table class="score-table">
                        <tr>
                            <th>Игрок</th>
                            <th>Sets</th>
                            <th>Games</th>
                            <th>Points</th>
                        </tr>
                        <tr>
                            <td>Player1</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                        </tr>
                        <tr>
                            <td>Player2</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                        </tr>
                    </table>
                </div>
                <div class="match-commands">
                    <form>
                        <button type="submit">Выигрывает Игрок №1</button>
                    </form>
                    <form>
                        <button type="submit">Выигрывает Игрок №2</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>