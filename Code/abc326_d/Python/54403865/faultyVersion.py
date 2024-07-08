n = int(input())
rowLeft, colUp = input(), input()
grid = [['.' for _ in range(n)] for _ in range(n)]
alphabet = ('A', 'B', 'C', '.')
rows = [{'A': False, 'B': False, 'C': False} for _ in range(n)]
cols = [{'A': False, 'B': False, 'C': False} for _ in range(n)]
rLeftPos = [n] * n
cUpPos = [n] * n

def valid(r: int, c: int, ch: chr) -> bool:
    res = True
    
    if ch != '.':

        res = rows[r][ch] == False and cols[c][ch] == False
    
        if c < rLeftPos[r]:
            res = res & (rowLeft[r] == ch)
        if r < cUpPos[c]:
            res = res & (colUp[c] == ch)
            
    if ch == '.':
        res = n - r - 1 >= 3 - sum(cols[c].values()) and n - c - 1 >= 3 - sum(rows[r].values())
    return res

def setChar(r: int, c: int, ch: chr):
    grid[r][c] = ch
    
    if ch != '.':
        rows[r][ch] = True
        cols[c][ch] = True
        
        if r < cUpPos[c]:
            cUpPos[c] = r
        if c < rLeftPos[r]:
            rLeftPos[r] = c

def removeChar(r: int, c:int) -> bool:
    ch = grid[r][c]
    
    if ch != '.':
        rows[r][ch] = False
        cols[c][ch] = False
    
    
        if r == cUpPos[c]:
            cUpPos[c] = n
        if c == rLeftPos[r]:
            rLeftPos[r] = n
            
    grid[r][c] = '.'

def fillPuzzle(r: int, c: int) -> bool:
    # print(sum(cols[r].values()))
    
    if r >= n:
        return True
    if c >= n:
        return fillPuzzle(r + 1, 0)
    
    for ch in alphabet:
        if valid(r, c, ch):
            setChar(r, c, ch)
            if fillPuzzle(r, c + 1):
                return True
            removeChar(r, c)
    return False        
    
if fillPuzzle(0, 0):
    print('Yes')
    for line in grid:
        print(line)
else:
    print('No')
# print(sum(rows[0].values()))