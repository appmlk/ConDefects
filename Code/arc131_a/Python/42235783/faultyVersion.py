A = int(input())
B = int(input())
if B%2==1:
    B *= 10
C = B//2

# A = list(str(A))
# print(A)
D = len(str(C))
print(A*(10**D)+C)