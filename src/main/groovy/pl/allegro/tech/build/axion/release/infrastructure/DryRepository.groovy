package pl.allegro.tech.build.axion.release.infrastructure

import pl.allegro.tech.build.axion.release.domain.logging.ReleaseLogger
import pl.allegro.tech.build.axion.release.domain.scm.ScmIdentity
import pl.allegro.tech.build.axion.release.domain.scm.ScmPosition
import pl.allegro.tech.build.axion.release.domain.scm.ScmPushOptions
import pl.allegro.tech.build.axion.release.domain.scm.ScmRepository

import java.util.List;
import java.util.regex.Pattern

import org.ajoberstar.grgit.Branch;

class DryRepository implements ScmRepository {

    private static final ReleaseLogger logger = ReleaseLogger.Factory.logger(DryRepository)

    private final ScmRepository delegateRepository

    DryRepository(ScmRepository delegateRepository) {
        this.delegateRepository = delegateRepository
    }

    @Override
    List<Branch> listAllBranches() {
        log('fetching branches from remote')
        return delegateRepository.listAllBranches()
    }

    @Override
    void fetchTags(ScmIdentity identity, String remoteName) {
        log('fetching tags from remote')
        delegateRepository.fetchTags(identity, remoteName)
    }

    @Override
    void tag(String tagName) {
        log("creating tag with name: $tagName")
    }

    @Override
    void branch(String branchName) {
        log("creating branch with name: branchName")
    }

    @Override
    void push(ScmIdentity identity, ScmPushOptions pushOptions) {
        log("pushing to remote: ${pushOptions.remote}")
    }

    @Override
    void commit(List patterns, String message) {
        log("commiting files matching $patterns with message: $message")
    }

    @Override
    void attachRemote(String remoteName, String url) {
        log("attaching remote: $remoteName")
    }

    @Override
    String currentBranch() {
        return delegateRepository.currentBranch()
    }

    @Override
    ScmPosition currentPosition(Pattern tagPattern, Pattern inversePattern) {
        return currentPosition(tagPattern)
    }

    @Override
    ScmPosition currentPosition(Pattern tagPattern) {
        ScmPosition position = delegateRepository.currentPosition(tagPattern)
        log("scm position: $position")
        return position
    }

    @Override
    boolean remoteAttached(String remoteName) {
        return true
    }

    @Override
    boolean checkUncommittedChanges() {
        boolean uncommittedChanges = delegateRepository.checkUncommittedChanges()
        log("uncommitted changes: $uncommittedChanges")
        return uncommittedChanges
    }

    @Override
    boolean checkAheadOfRemote() {
        boolean aheadOfRemote = delegateRepository.checkAheadOfRemote()
        log("ahead of remote: $aheadOfRemote")
        return aheadOfRemote
    }

    @Override
    List<String> lastLogMessages(int messageCount) {
        return delegateRepository.lastLogMessages(messageCount)
    }

    private void log(String msg) {
        logger.quiet("DRY-RUN: $msg")
    }
}
