<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Матч: ${match.firstPlayer.name} vs ${match.secondPlayer.name}</title>
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
            <p class="page-header">Матч: ${match.firstPlayer.name} vs ${match.secondPlayer.name}</p>
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
                            <td>${match.firstPlayer.name}</td>
                            <td>${match.score.firstPlayerSets}</td>
                            <td>${match.score.firstPlayerGames}</td>
                            <td>${match.score.firstPlayerPoints}</td>
                        </tr>
                        <tr>
                            <td>${match.secondPlayer.name}</td>
                            <td>${match.score.secondPlayerSets}</td>
                            <td>${match.score.secondPlayerGames}</td>
                            <td>${match.score.secondPlayerPoints}</td>
                        </tr>
                    </table>
                </div>
                <div class="match-commands">
                    <form method="POST" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="point-winner-id" value="${match.firstPlayer.id}">
                        <button type="submit">Выигрывает ${match.firstPlayer.name}</button>
                    </form>
                    <form method="POST" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="point-winner-id" value="${match.secondPlayer.id}">
                        <button type="submit">Выигрывает ${match.secondPlayer.name}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>