def find_winner(seq):
    if (max(seq) - len(seq)) % 2 == 0:
        return 'Alice'
    else:
        return 'Bob'
    
n = int(input())
seq = [int(num) for num in input().split()]
print(find_winner(seq))