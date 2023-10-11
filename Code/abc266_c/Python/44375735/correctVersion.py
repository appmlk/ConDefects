# 凸四角形の十分条件→対角線が交わればOK
def getIntTuple():
    return tuple(map(int, input().split()))


def isIntersect(A, B, C, D):
    tb = (B[1] - A[1])*(C[0] - A[0])-(C[1] - A[1])*(B[0] - A[0])
    td = (D[1] - A[1])*(C[0] - A[0])-(C[1] - A[1])*(D[0] - A[0])

    ta = (A[1] - B[1])*(D[0] - B[0])-(D[1] - B[1])*(A[0] - B[0])
    tc = (C[1] - B[1])*(D[0] - B[0])-(D[1] - B[1])*(C[0] - B[0])
    return tb * td < 0 and ta * tc < 0


def main():
    A = getIntTuple()
    B = getIntTuple()
    C = getIntTuple()
    D = getIntTuple()

    print("Yes" if isIntersect(A, B, C, D) else "No")


if __name__ == "__main__":
    main()
