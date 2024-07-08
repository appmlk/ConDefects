MOD = 998244353
N = int(input())
P = list(map(lambda x: int(x)-1, input().split()))
S = input()

def pow(a, b):
    if b==0:
        return 1
    ret = pow((a**2)%MOD, b//2)
    if b%2:
        ret = ret*a%MOD
    return ret

def countL():
    cnt = 0
    spoon = [False]*N
    for p in P:
        match S[p]:
            case "L":
                spoon[p] = True
            case "R":
                if not spoon[(p+1)%N]:
                    return 0
                spoon[p] = True
            case "?":
                if spoon[(p+1)%N]:
                    cnt += 1
                spoon[p] = True
    return pow(2,cnt)

def countR():
    cnt = 0
    spoon = [False]*N
    for p in P:
        match S[p]:
            case "R":
                spoon[(p+1)%N] = True
            case "L":
                if not spoon[p]:
                    return 0
                spoon[(p+1)%N] = True
            case "?":
                if spoon[p]:
                    cnt += 1
                spoon[(p+1)%N] = True
    return pow(2,cnt)

ans = 0
match S[P[0]]:
    case "L":
        ans = countL()
    case "R":
        ans = countR()
    case "?":
        ans = countL() + countR()
print(ans)
