def hantei(N,A):
    for n1 in range(N):
        for n2 in range(N):
            if ((A[n1][n2] == "-" and A[n2][n1] == "-") 
                or (A[n1][n2] == "W" and A[n2][n1] == "L") 
                or (A[n1][n2] == "L" and A[n2][n1] == "W") 
                or (A[n1][n2] == "D" and A[n2][n1] == "D")):
                continue
            else:
                return "incorrect"
    return "correnct"

N = int(input())
A = []

for _ in range(N):
    A.append(list(str(input())))

print(hantei(N,A))

