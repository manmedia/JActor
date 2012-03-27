/*
 * Copyright 2011 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.jactor.components.factory;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.components.Include;
import org.agilewiki.jactor.components.JCActor;

/**
 * Creates a JCActor.
 */
final public class JCActorFactory extends ActorFactory {
    /**
     * The class of the root component.
     */
    private Class componentClass;

    /**
     * Create an ActorFactory.
     *
     * @param actorType      The actor type.
     * @param componentClass The class of the root component.
     */
    public JCActorFactory(String actorType, Class componentClass) {
        super(actorType);
        this.componentClass = componentClass;
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    @Override
    public JCActor newActor(Mailbox mailbox, Actor parent) throws Exception {
        Include include = new Include(componentClass);
        JCActor actor = new JCActor(mailbox);
        actor.setActorType(actorType);
        actor.setParent(parent);
        include.call(actor);
        return actor;
    }
}
