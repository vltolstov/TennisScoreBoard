<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Матч: ${match.playerOne.name} vs ${match.playerTwo.name}</title>
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
            <p class="page-header">Матч: ${match.playerOne.name} vs ${match.playerTwo.name}</p>
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
                            <td>${match.playerOne.name}</td>
                            <td class="player-one-sets">${match.score.playerOneSets}</td>
                            <td class="player-one-games">${match.score.playerOneGames}</td>
                            <td class="player-one-points">${match.score.playerOnePoints}</td>
                        </tr>
                        <tr>
                            <td>${match.playerTwo.name}</td>
                            <td class="player-two-sets">${match.score.playerTwoSets}</td>
                            <td class="player-two-games">${match.score.playerTwoGames}</td>
                            <td class="player-two-points">${match.score.playerTwoPoints}</td>
                        </tr>
                    </table>
                </div>
                <div class="match-commands">
                    <form method="POST" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="player-id" value="${match.playerOne.id}">
                        <button type="submit">Выигрывает ${match.playerOne.name}</button>
                    </form>
                    <form method="POST" action="match-score?uuid=${uuid}">
                        <input type="hidden" name="player-id" value="${match.playerTwo.id}">
                        <button type="submit">Выигрывает ${match.playerTwo.name}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>