/*******************************************************************************
 * Copyright (c) 2012, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.tycho.plugins.p2.publisher;

import java.io.File;
import java.util.Collection;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.tycho.core.resolver.shared.DependencySeed;
import org.eclipse.tycho.p2.tools.FacadeException;
import org.eclipse.tycho.p2.tools.publisher.facade.PublisherService;

/**
 * <p>
 * Publishes a custom execution environment profile.
 * </p>
 * 
 * @goal publish-ee-profile
 * @phase prepare-package
 * @since 0.16.0
 */
public final class PublishEEProfileMojo extends AbstractPublishMojo {

    /**
     * <p>
     * The profile file containing the execution environment definition.
     * </p>
     * 
     * @parameter
     * @required
     */
    private File profileFile;

    @Override
    protected Collection<DependencySeed> publishContent(PublisherService publisherService)
            throws MojoExecutionException, MojoFailureException {
        try {
            Collection<DependencySeed> ius = publisherService.publishEEProfile(profileFile);
            getLog().info("Published profile IUs: " + ius);
            return ius;
        } catch (FacadeException e) {
            throw new MojoExecutionException("Exception while publishing execution environment profile " + profileFile
                    + ": " + e.getMessage(), e);
        }
    }
}
