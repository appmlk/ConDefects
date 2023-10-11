N = int(input())

def range(n):
    if(n < 10**3):
        return 0
    elif(10**3 <= n < 10**4):
        return 1
    elif(10**4 <= n < 10**5):
        return 2
    elif(10**5 <= n < 10**6):
        return 3
    elif(10**6 <= n < 10**7):
        return 4
    elif(10**7 <= n < 10**8):
        return 5
    elif(10**8 <= n < 10**9):
        return 6
    
print(round(N, -range(N)))