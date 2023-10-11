N, A, B, C, D = map(int, input().split())

if abs(B - C) > 1:
    print("No")
    exit()
elif B == C:
    if B == 0:
        if A > 0 and D == 0:
            if N == A + 1:
                print("Yes")
            else:
                print("No")
        elif A == 0 and D > 0:
            if N == D + 1:
                print("Yes")
            else:
                print("No")
        elif A == 0 and D == 0:
            if N == 0:
                print("Yes")
            else:
                print("No")
        else:
            print("No")
    else:
        if B * 2 + 1 + A + D == N:
            print("Yes")
        else:
            print("No")

else:
    if max(B, C) * 2 + A + D == N:
        print("Yes")
    else:
        print("No")
    
    