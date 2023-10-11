T = int(input())
def compare(t1, t2):
    length = min(len(t1), len(t2))
    for i in range(length):
        if t1[i] > t2[i]:
            return False
        if t1[i] < t2[i]:
            return True
    if len(t1) >= len(t2):
        return False
    else:
        return True

for i in range(T):
    N = int(input())
    S = input()
    flag = False
    for i in range(1, N):
        tem1 = S[:i]
        tem2 = S[i:]
        if compare(tem1, tem2):
            flag = True
            break
    if not flag:
        print("No")
    else:
        print("Yes")