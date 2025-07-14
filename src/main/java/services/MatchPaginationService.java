package services;

import dao.MatchDao;
import dto.PaginationResultDto;
import models.Match;
import models.Player;

import java.util.List;

public class MatchPaginationService {
    private MatchDao matchDAO;

    public MatchPaginationService(MatchDao matchDAO) {
        this.matchDAO = matchDAO;
    }

    public PaginationResultDto<Match> getMatchesPage(int page, int pageSize) {
        Long totalMatches = matchDAO.getTotalMatchesCount();
        List<Match> matches = matchDAO.findAll(page, pageSize);
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);

        return new PaginationResultDto<>(matches, page, totalPages);
    }

    public PaginationResultDto<Match> getMatchesPage(int page, int pageSize, Player player) {
        Long totalMatches = matchDAO.getTotalMatchesCountByPlayer(player);
        List<Match> matches = matchDAO.findByPlayer(player, page, pageSize);
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);

        return new PaginationResultDto<>(matches, page, totalPages);
    }
}
