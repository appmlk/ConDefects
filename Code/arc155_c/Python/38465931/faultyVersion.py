import sys
import os
import inspect
input = sys.stdin.buffer.readline
sys.setrecursionlimit(10 ** 7)

if os.getenv("TKTKLOCAL", False):
    def debug(*arg, sep=" ", end="\n"):
        print(*arg, sep=sep, end=end, file=sys.stderr)

    def debug_indent(*arg, sep=" ", end="\n", indent="    "):
        frame = inspect.currentframe().f_back
        par_func = inspect.getframeinfo(frame).function
        if par_func == "<module>":
            debug(*arg, sep=sep, end=end)
            return

        frame_stack = inspect.stack()
        if len(frame_stack) > 30:
            return

        depth = sum(f.function == par_func for f in frame_stack)
        debug(indent * (depth - 1), end="")
        debug(*arg, sep=sep, end=end)
else:
    def debug(*arg, **kwarg):
        pass

    def debug_indent(*arg, **kwarg):
        pass


def reorder(A):
    res = []
    tmp = []
    for a in A:
        if a % 2 == 0:
            tmp.append(a)
        else:
            tmp.sort()
            res.extend(tmp)
            res.append(a)
            tmp = []
    if tmp:
        tmp.sort()
        res.extend(tmp)
    return res


def check(A, B):
    A0 = []
    A1 = []
    B0 = []
    B1 = []
    tmp = []
    for a in A:
        if a % 2 == 1:
            A1.append(a)
            if tmp:
                if len(tmp) > 2:
                    tmp.sort()
                A0.append(tmp)
                tmp = []
        else:
            tmp.append(a)
    if tmp:
        if len(tmp) > 2:
            tmp.sort()
        A0.append(tmp)
        tmp = []
    for a in B:
        if a % 2 == 1:
            B1.append(a)
            if tmp:
                if len(tmp) > 2:
                    tmp.sort()
                B0.append(tmp)
                tmp = []
        else:
            tmp.append(a)
    if tmp:
        if len(tmp) > 2:
            tmp.sort()
        B0.append(tmp)
    debug(A0, B0)
    debug(A1, B1)
    return A0 == B0 and A1 == B1


def main():
    N = int(input())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    # A = reorder(A)
    # B = reorder(B)
    debug(A)
    debug(B)

    if sorted(A) != sorted(B):
        return False

    if A == B:
        return True

    Ta = [a % 2 for a in A]
    Tb = [b % 2 for b in B]
    debug(Ta, Tb)

    ok = 0
    okB = 0
    for i in range(N - 2):
        if sum(Ta[i:i+3]) == 2:
            ok = 1
        if sum(Tb[i:i+3]) == 2:
            okB = 1
    debug(ok, okB)
    if ok == 1 and okB == 0:
        return False

    if not ok:
        return check(A, B)

    if sum(Ta) == 2:
        X = [a for a in A if a % 2 == 0]
        Y = [a for a in B if a % 2 == 0]
        return X == Y
    return True


if main():
    print("Yes")
else:
    print("No")