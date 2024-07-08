T = int(input())
for _ in range(T):
    N = int(input())
    A = sorted(list(map(int, input().split())))
    Odd = 0
    Test = 0
    Em = 0
    for i in A:
        if i % 2 == 1:
            Odd += 1
            if Odd % 2 == 0 and i >= Odd+1:
                print("Yes")
                Test = 1
                break
        else:
            Em = max(Em, i)
    if Test == 1:
        continue
    if Em > Odd:
        print("Yes")
        continue
    print("No")
