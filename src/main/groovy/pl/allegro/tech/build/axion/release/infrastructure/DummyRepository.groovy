package pl.allegro.tech.build.axion.release.infrastructure

import pl.allegro.tech.build.axion.release.domain.logging.ReleaseLogger
import pl.allegro.tech.build.axion.release.domain.scm.ScmIdentity
import pl.allegro.tech.build.axion.release.domain.scm.ScmPosition
import pl.allegro.tech.build.axion.release.domain.scm.ScmPushOptions
import pl.allegro.tech.build.axion.release.domain.scm.ScmRepository

import java.util.List;
import java.util.regex.Pattern

import org.ajoberstar.grgit.Branch;

class DummyRepository implements ScmRepository {

    private static final ReleaseLogger logger = ReleaseLogger.Factory.logger(DummyRepository)

    DummyRepository() {
    }

    private void log(String commandName) {
        logger.quiet("Couldn't perform $commandName command on uninitialized repository")
    }

    @Override
    List<Branch> listAllBranches() {
        log('list all branches')
        def remoteBranch = new Branch(fullName: 'remotes/origin/master')
        return [new Branch(fullName: 'refs/heads/master', trackingBranch: remoteBranch), remoteBranch]
    }

    @Override
    void fetchTags(ScmIdentity identity, String remoteName) {
        log('fetch tags')
    }

    @Override
    void tag(String tagName) {
        log('create tag')
    }

    @Override
    void branch(String tagName) {
        log('create branch')
    }

    @Override
    void push(ScmIdentity identity, ScmPushOptions pushOptions) {
        log('push')
    }

    @Override
    void commit(List patterns, String message) {
        log('commit')
    }

    @Override
    void attachRemote(String remoteName, String url) {
        log('attach remote')
    }

    @Override
    String currentBranch() {
        return 'master'
    }

    @Override
    ScmPosition currentPosition(Pattern tagPattern) {
        logger.quiet("Could not resolve current position on uninitialized repository, returning default")
        return ScmPosition.defaultPosition()
    }

    @Override
    ScmPosition currentPosition(Pattern tagPattern, Pattern inversePattern) {
        return currentPosition(tagPattern)
    }

    @Override
    boolean remoteAttached(String remoteName) {
        log('remote attached')
        return false
    }

    @Override
    boolean checkUncommittedChanges() {
        log('check uncommitted changes')
        return false
    }

    @Override
    boolean checkAheadOfRemote() {
        log('check ahead of remote')
        return false
    }

    @Override
    List<String> lastLogMessages(int messageCount) {
        log('last log messages')
        return null
    }
}

