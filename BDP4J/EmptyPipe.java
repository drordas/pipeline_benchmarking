/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdp4j_evaluator;

import org.bdp4j.pipe.AbstractPipe;
import org.bdp4j.types.Instance;

/**
 *
 * @author María Novo
 */
public class EmptyPipe extends AbstractPipe {

    public EmptyPipe() {
        super(new Class<?>[0], new Class<?>[0]);
    }

    @Override
    public Class<?> getInputType() {
        return String.class;
    }

    @Override
    public Class<?> getOutputType() {
        return String.class;
    }

    @Override
    public Instance pipe(Instance carrier) {
        return carrier;
    }
}
