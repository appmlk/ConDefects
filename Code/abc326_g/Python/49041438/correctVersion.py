from enum import Enum
from typing import SupportsFloat

import numpy as np
from scipy.optimize import linprog  # type: ignore


class LPVariableType(Enum):
    CONTINUOUS = 1
    INTEGER = 2


class LPBase:
    def __add__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return AffineExpression.build(self) + other

    def __radd__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return other + AffineExpression.build(self)

    def __sub__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return AffineExpression.build(self) - other

    def __rsub__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return other - AffineExpression.build(self)

    def __mul__(self, other: SupportsFloat) -> "AffineExpression":
        return AffineExpression.build(self) * other

    def __rmul__(self, other: SupportsFloat) -> "AffineExpression":
        return AffineExpression.build(self) * other

    def __truediv__(self, other: SupportsFloat) -> "AffineExpression":
        return AffineExpression.build(self) / other

    def __neg__(self) -> "AffineExpression":
        return -AffineExpression.build(self)

    def __le__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(
            lhs=AffineExpression.build(self),
            rhs=other,
            inequality_type=InequalityType.LESSEQ,
        )

    def __ge__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(
            lhs=other,
            rhs=AffineExpression.build(self),
            inequality_type=InequalityType.LESSEQ,
        )

    def __eq__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(
            lhs=AffineExpression.build(self),
            rhs=other,
            inequality_type=InequalityType.EQUAL,
        )


class LPVariable(LPBase):
    def __init__(
        self,
        name: str,
        lower_bound: float | None = None,
        upper_bound: float | None = None,
        variable_type: LPVariableType = LPVariableType.CONTINUOUS,
    ) -> None:
        self.name = name
        self.lower_bound: float | None = lower_bound
        self.upper_bound: float | None = upper_bound
        self.variable_type: LPVariableType = variable_type
        self._value: float | None = None

    def __str__(self) -> str:
        s = "{}(lb={}, ub={}, type={})".format(
            self.name,
            self.lower_bound,
            self.upper_bound,
            self.variable_type.name,
        )
        if self._value is not None:
            s += ": {}".format(self._value)
        return s

    def __repr__(self) -> str:
        return str(self)

    def value(self) -> float | None:
        return self._value


class InequalityType(Enum):
    LESSEQ = 1  # lhs <= rhs
    EQUAL = 2  # lhs == rhs


class _FirstDegreeTerm(LPBase):
    """
    A term of the form coefficient * variable, where coefficient is a real
    """

    def __init__(self, coefficient: float, variable: LPVariable) -> None:
        self.coefficient: float = coefficient
        self.variable: LPVariable = variable


class AffineExpression:
    def __init__(
        self,
        const: SupportsFloat,
        terms: list[_FirstDegreeTerm],
    ) -> None:
        self.const = float(const)
        self.terms = terms.copy()
        self._value: float | None = None

    @classmethod
    def build(self, x: "AffineExpressionLike | LPBase") -> "AffineExpression":
        if isinstance(x, AffineExpression):
            return AffineExpression(x.const, x.terms)
        elif isinstance(x, _FirstDegreeTerm):
            return AffineExpression(0, [x])
        elif isinstance(x, LPVariable):
            return AffineExpression(0, [_FirstDegreeTerm(1, x)])
        elif isinstance(x, SupportsFloat):
            return AffineExpression(x, [])
        else:
            raise TypeError("Invalid type for AffineExpression")

    def __add__(self, other: "AffineExpressionLike") -> "AffineExpression":
        rhs = AffineExpression.build(other)
        return AffineExpression(
            self.const + rhs.const,
            self.terms + rhs.terms,
        )

    def __radd__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return self + other

    def __sub__(self, other: "AffineExpressionLike") -> "AffineExpression":
        rhs = AffineExpression.build(other)
        return self + (-rhs)

    def __rsub__(self, other: "AffineExpressionLike") -> "AffineExpression":
        return AffineExpression.build(other) + (-self)

    def __mul__(self, other: SupportsFloat) -> "AffineExpression":
        return AffineExpression(
            self.const * float(other),
            [
                _FirstDegreeTerm(t.coefficient * float(other), t.variable)
                for t in self.terms
            ],
        )

    def __rmul__(self, other: SupportsFloat) -> "AffineExpression":
        return self * other

    def __truediv__(self, other: SupportsFloat) -> "AffineExpression":
        return AffineExpression(
            self.const / float(other),
            [
                _FirstDegreeTerm(t.coefficient / float(other), t.variable)
                for t in self.terms
            ],
        )

    def __neg__(self) -> "AffineExpression":
        return AffineExpression(
            -self.const,
            [_FirstDegreeTerm(-t.coefficient, t.variable) for t in self.terms],
        )

    def __le__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(self, other, InequalityType.LESSEQ)

    def __ge__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(other, self, InequalityType.LESSEQ)

    def __eq__(
        self,
        other: "AffineExpressionLike",
    ) -> "LPInequality":  # type: ignore
        return LPInequality(self, other, InequalityType.EQUAL)

    def __str__(self) -> str:
        ret = ["{}".format(self.const)]

        for t in self.terms:
            sgn = "+" if t.coefficient >= 0 else ""
            ret.append("{}{}{}".format(sgn, t.coefficient, t.variable.name))

        return " ".join(ret)

    def value(self) -> float | None:
        return self._value


AffineExpressionLike = AffineExpression | _FirstDegreeTerm | LPVariable | SupportsFloat


class LPInequality:
    def __init__(
        self,
        lhs: AffineExpressionLike,
        rhs: AffineExpressionLike,
        inequality_type: InequalityType,
    ) -> None:
        """
        lhs <= rhs
        -> terms + const (<= or ==) 0
        """

        self.lhs: AffineExpression = AffineExpression.build(
            lhs
        ) - AffineExpression.build(rhs)
        self.inequality_type = inequality_type

    def __str__(self) -> str:
        if self.inequality_type == InequalityType.LESSEQ:
            return "{} <= 0".format(self.lhs)
        elif self.inequality_type == InequalityType.EQUAL:
            return "{} == 0".format(self.lhs)
        else:
            raise ValueError("Invalid inequality type")

    def __repr__(self) -> str:
        return str(self)


class LPModel:
    def __init__(self, name: str) -> None:
        self.name = name
        self.constraints: list[LPInequality] = []
        self.objective: AffineExpression = AffineExpression(0, [])

    def add_constraint(self, constraint: LPInequality):
        self.constraints.append(constraint)

    def set_objective(self, objective: AffineExpressionLike) -> None:
        self.objective = AffineExpression.build(objective)

    def solve(self) -> None:
        var_dict: dict[int, LPVariable] = {}
        for constraint in self.constraints:
            for term in constraint.lhs.terms:
                var_dict.setdefault(id(term.variable), term.variable)

        for term in self.objective.terms:
            var_dict.setdefault(id(term.variable), term.variable)

        # print(var_dict)

        id_to_idx = {id(v): i for i, v in enumerate(var_dict.values())}
        # print(id_to_idx)

        A_ub = np.zeros((0, len(var_dict)))
        b_ub = np.zeros(0)
        A_eq = np.zeros((0, len(var_dict)))
        b_eq = np.zeros(0)

        for constraint in self.constraints:
            # print(constraint)
            lhs = np.zeros(len(var_dict))
            rhs = -constraint.lhs.const

            for term in constraint.lhs.terms:
                lhs[id_to_idx[id(term.variable)]] += term.coefficient

            if constraint.inequality_type == InequalityType.LESSEQ:
                A_ub = np.vstack((A_ub, lhs))
                b_ub = np.hstack((b_ub, rhs))
            elif constraint.inequality_type == InequalityType.EQUAL:
                A_eq = np.vstack((A_eq, lhs))
                b_eq = np.hstack((b_eq, rhs))
            else:
                raise ValueError("Invalid inequality type")

        bounds = [(v.lower_bound, v.upper_bound) for v in var_dict.values()]

        integrality = [
            int(variable.variable_type == LPVariableType.INTEGER)
            for variable in var_dict.values()
        ]

        c = np.zeros(len(var_dict))

        for term in self.objective.terms:
            c[id_to_idx[id(term.variable)]] += term.coefficient

        res = linprog(
            c,
            A_ub=A_ub,
            b_ub=b_ub,
            A_eq=A_eq,
            b_eq=b_eq,
            bounds=bounds,
            integrality=integrality,
        )

        for i, variable in enumerate(var_dict.values()):
            variable._value = res.x[i]
            # print(variable)

        # print(res)

        self.objective._value = res.fun + self.objective.const


N, M = map(int, input().split())
C = list(map(int, input().split()))
A = list(map(int, input().split()))
L = [list(map(int, input().split())) for _ in range(M)]

xs = [
    LPVariable(
        "x{}".format(i),
        lower_bound=1,
        upper_bound=5,
    )
    for i in range(N)
]

ys = [
    LPVariable(
        "y{}".format(i),
        lower_bound=0,
        upper_bound=1,
        variable_type=LPVariableType.INTEGER,
    )
    for i in range(M)
]

problem = LPModel("atcoder326G")

for i in range(M):
    for j in range(N):
        problem.add_constraint(xs[j] - L[i][j] * ys[i] >= 0)

objective = 0
for i in range(M):
    objective -= A[i] * ys[i]
for i in range(N):
    objective += C[i] * (xs[i] - 1)
problem.set_objective(objective)

problem.solve()

print(round(-problem.objective.value()))
