package org.graalphp.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.runtime.array.PhpArray;
import org.graalphp.types.PhpFunction;
import org.graalphp.types.PhpTypesGen;

/**
 * Node which returns a value.
 *
 * @author abertschi
 */
@NodeInfo(description = "Abstract class for expression")
public abstract class PhpExprNode extends PhpStmtNode {

    @Override
    public void executeVoid(VirtualFrame frame) {
        executeGeneric(frame);
    }

    /**
     * Execute method for no type specializations
     */
    public abstract Object executeGeneric(VirtualFrame frame);

    public long executeLong(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectLong(this.executeGeneric(f));
    }

    public boolean executeBoolean(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectBoolean(this.executeGeneric(f));
    }

    public double executeDouble(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectDouble(this.executeGeneric(f));
    }

    public PhpArray executePhpArray(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectPhpArray(this.executeGeneric(f));
    }

    public PhpFunction executePhpFunction(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectPhpFunction(this.executeGeneric(f));
    }
}
