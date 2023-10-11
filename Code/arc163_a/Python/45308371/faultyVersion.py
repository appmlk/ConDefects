T = int(input())
for i in range(T):
    N = int(input())
    S = input()
    a = False
    for j in range(1, N):
        if S[0:j] < S[j]:
            print("Yes")
            a = True
            break
        elif S[0] == S[j]:
            b = False
            nex = 1
            while not b:
                nex += 1
                if j + nex >= N:
                    break
                if S[0:j] < S[j:j + nex]:
                    print("Yes")
                    a = True
                    b = True
            if b:
                break
    if not a:
        print("No")