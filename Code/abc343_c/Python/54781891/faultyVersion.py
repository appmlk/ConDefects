#1603

N = int(input())

A = []

for i in range(0, 10**6+1):
    B = i**3
    if B >= N:
        break
    elif str(B) == str(B)[::-1]:
        A.append(B)

print(max(A))