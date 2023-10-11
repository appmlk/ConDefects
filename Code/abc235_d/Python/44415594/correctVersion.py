from sys import stdin, setrecursionlimit
a, n = list(map(int, input().split()))

setrecursionlimit(10 ** 9)
numSet = [10**5] * (10 ** 7)
ans = 10 ** 10

def numSrch(c, moves):
    global numSet
    # print(c, numSet[c], moves)
    if c == 1:
        global ans
        ans = min(ans, moves)
        return
    elif numSet[c] > moves:
        # print(c)
        numSet[c] = moves

        if c % a == 0:
            c1 = int(c//a)
            if numSet[c1] > moves+1:
                numSrch(c1, moves+1)
        if c > 11:
            c2 = str(c)
            c2 = "".join([c2[1:], c2[0]])
            # print(c, c2)
            c2 = int(c2)
            if len(str(c2)) == len(str(c)) and numSet[c2] > moves+1:
                numSrch(c2, moves+1)

numSrch(n, 0)
if ans == 10 ** 10:
    ans = -1
print(ans)