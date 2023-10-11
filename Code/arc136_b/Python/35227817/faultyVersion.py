def main():
    N = int(input())
    *A, = map(int, input().split())
    *B, = map(int, input().split())
    X = sorted(A)
    Y = sorted(B)
    if X != Y:
        print("No")
        return
    if len(set(A)) == len(A):
        print("Yes")
        return
    ct = 0
    for b in B:
        x = A.index(b)
        ct += x
        A.pop(x)
    if ct%2 == 0:
        print("Yes")
    else:
        print("No")
if __name__ == "__main__":
    main()