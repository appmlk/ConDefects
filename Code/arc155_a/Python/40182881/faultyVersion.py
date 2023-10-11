def main():
    T = int(input())
    for _ in range(T):
        N, K = map(int, input().split())
        S = list(input())
        if K < N:
            L1 = S + S[:K][::-1]
            L2 = S[:K][::-1] + S
            for i in range(N+K):
                if L1[i] != L1[-i-1] or L2[i] != L2[-i-1]:
                    break
            else:
                print("Yes")
                continue
            print("No")
            continue
        l = N
        for i in range(1, N+1):
            if not N % i == 0:
                continue
            for j in range(N // i):
                if j % 2 == 1:
                    for k in range(i):
                        
                        if S[i*(j+1)-k-1] != S[k]:
                            break
                    else:
                        continue
                    break
                else:
                    for k in range(i):
                        
                        if S[i*j + k] != S[k]:
                            break
                    else:
                        continue
                    break
            else:
                l = min(l, i)
                break
        if (N+K) % (2*l) == 0:
            print("Yes")
            continue
        elif (N+k) % l == 0:
            for i in range(l):
                if S[i] != S[l-i-1]:
                    break
            else:
                print("Yes")
                continue
            print("No")
            continue
        else:
            print("No")
            continue



if __name__ == "__main__":
    main()