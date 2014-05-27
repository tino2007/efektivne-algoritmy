# coding=utf-8

import os

tasks = u'''
1.1 110101 100 The 3n + 1 problem
1.2 110102 10189 Minesweeper
1.3 110103 10137 The Trip
1.4 110104 706 LCD Display
1.5 110105 10267 Graphical Editor
1.6 110106 10033 Interpreter
1.7 110107 10196 Check the Check
1.8 110108 10142 Australian Voting
2.1 110201 10038 Jolly Jumpers
2.2 110202 10315 Poker Hands
2.3 110203 10050 Hartals
2.4 110204 843 Crypt Kicker
2.5 110205 10205 Stack ’em Up
2.6 110206 10044 Erdos Numbers
2.7 110207 10258 Contest Scoreboard
2.8 110208 10149 Yahtzee
3.1 110301 10082 WERTYU
3.2 110302 10010 Where’s Waldorf?
3.3 110303 10252 Common Permutation
3.4 110304 850 Crypt Kicker II
3.5 110305 10188 Automated Judge Script
3.6 110306 10132 File Fragmentation
3.7 110307 10150 Doublets
3.8 110308 848 Fmt
4.1 110401 10041 Vito’s Family
4.2 110402 120 Stacks of Flapjacks
4.3 110403 10037 Bridge
4.4 110404 10191 Longest Nap
4.5 110405 10026 Shoemaker’s Problem
4.6 110406 10138 CDVII
4.7 110407 10152 ShellSort
4.8 110408 10194 Football (aka Soccer)
5.1 110501 10035 Primary Arithmetic
5.2 110502 10018 Reverse and Add
5.3 110503 701 The Archeologist’s Dilemma
5.4 110504 10127 Ones
5.5 110505 847 A Multiplication Game
5.6 110506 10105 Polynomial Coeﬃcients
5.7 110507 10077 Stern-Brocot Number System
5.8 110508 10202 Pairsumonious Numbers
6.1 110601 10183 How Many Fibs?
6.2 110602 10213 How Many Pieces of Land?
6.3 110603 10198 Counting
6.4 110604 10157 Expressions
6.5 110605 10247 Complete Tree Labeling
6.6 110606 10254 The Priest Mathematician
6.7 110607 10049 Self-describing Sequence
6.8 110608 846 Steps
7.1 110701 10110 Light, More Light
7.2 110702 10006 Carmichael Numbers
7.3 110703 10104 Euclid Problem
7.4 110704 10139 Factovisors
7.5 110705 10168 Summation of Four Primes
7.6 110706 10042 Smith Numbers
7.7 110707 10090 Marbles
7.8 110708 10089 Repackaging
8.1 110801 861 Little Bishops
8.2 110802 10181 15-Puzzle Problem
8.3 110803 10128 Queue
8.4 110804 10160 Servicing Stations
8.5 110805 10032 Tug of War
8.6 110806 10001 Garden of Eden
8.7 110807 704 Color Hash
8.8 110808 10270 Bigger Square Please...
9.1 110901 10004 Bicoloring
9.2 110902 10067 Playing With Wheels
9.3 110903 10099 The Tourist Guide
9.4 110904 705 Slash Maze
9.5 110905 10029 Edit Step Ladders
9.6 110906 10051 Tower of Cubes
9.7 110907 10187 From Dusk Till Dawn
9.8 110908 10276 Hanoi Tower Troubles Again!
10.1 111001 10034 Freckles
10.2 111002 10054 The Necklace
10.3 111003 10278 Fire Station
10.4 111004 10039 Railroads
10.5 111005 10158 War
10.6 111006 10199 Tourist Guide
10.7 111007 10249 The Grand Dinner
10.8 111008 10092 Problem With Problem Setter
11.1 111101 10131 Is Bigger Smarter?
11.2 111102 10069 Distinct Subsequences
11.3 111103 10154 Weights and Measures
11.4 111104 116 Unidirectional TSP
11.5 111105 10003 Cutting Sticks
11.6 111106 10261 Ferry Loading
11.7 111107 10271 Chopsticks
11.8 111108 10201 Adventures in Moving: Part IV
12.1 111201 10161 Ant on a Chessboard
12.2 111202 10047 The Monocycle
12.3 111203 10159 Star
12.4 111204 10182 Bee Maja
12.5 111205 707 Robbery
12.6 111206 10177 (2/3/4)-D Sqr/Rects/Cubes?
12.7 111207 10233 Dermuba Triangle
12.8 111208 10075 Airlines
13.1 111301 10310 Dog and Gopher
13.2 111302 10180 Rope Crisis in Ropeland!
13.3 111303 10195 Knights of the Round Table
13.4 111304 10136 Chocolate Chip Cookies
13.5 111305 10167 Birthday Cake
13.6 111306 10215 The Largest/Smallest Box ...
13.7 111307 10209 Is This Integration?
13.8 111308 10012 How Big Is It?
14.1 111401 10135 Herding Frosh
14.2 111402 10245 The Closest Pair Problem
14.3 111403 10043 Chainsaw Massacre
14.4 111404 10084 Hotter Colder
14.5 111405 10065 Useless Tile Packers
14.6 111406 849 Radar Tracking
14.7 111407 10088 Trees on My Island
14.8 111408 10117 Nice Milk
'''

data = u'''
Jaroslav  Beňo      110206  2   110408  1
Matej  Brašeň       110603  2   111105  2
Martin  Brzula      110904  2   110902  2
Juraj  Bubniak      110207  1   110204  2
Ján  Fekeš      111001  2   110406  2
Peter  Franček      110601  1   110705  2
Ladislav  Gubala        110107  1   110106  2
Ivan  Hraško        110908  3   110505  3
Pavel  Jančiar      110501  1   110203  2
Jakub  Jurčík       110805  2   110704  2
Nikoleta  Kabáčová      110304  2   110306  2
Peter  Kajan        110101  1   110402  2
Ján  Maár       110305  1   110802  3
Matúš  Marko        110607  2   111207  2
Jozef  Medveď       110701  1   111303  2
Tomáš  Nguyen       110103  1   110302  2
Matúš  Novysedlák       110205  1   110208  3
Peter  Otto     110303  1   110405  2
Radoslav  Rajčan        110102  1   110202  2
Matej  Tepper       110301  1   110608  2
Ján  Turský     110502  1   110504  2
Štefan  Ušák        110201  1   111302  2
Martin Jančo        110702    2   110703    1
'''


tasks = [task for task in tasks.split('\n') if task]
task_dict = {}

for task in tasks:
    splitted = task.split(' ', 3)

    title = splitted[3]

    if ',' in title:
        title = title.split(',')[0]

    task_dict[splitted[1]] = title


lines = [line.split() for line in data.split(u'\n') if line]
nazov = 'Nazov'
uloha = 'Odkaz'
obtiaznost = 'Obtiaznost'
student = 'Student'
hotove = 'Hotove'
root = os.path.abspath(os.path.dirname(__file__))

def existuje(idx):
    return 'Ano' if os.path.isdir(os.path.join(root, idx)) else 'Nie'


print '|',
print nazov.ljust(41),
print '|',
print uloha.ljust(18),
print '|',
print obtiaznost.ljust(11),
print '|',
print student.ljust(17),
print '|',
print hotove.ljust(8),
print '|'
print '|',
print 41 * '-',
print '|',
print 18 * '-',
print '|',
print 11 * '-',
print '|',
print 17 * '-',
print '|',
print 8 * '-',
print '|'

for name, lastname, id1, level1, id2, level2 in lines:
    fullname = u'{0} {1}'.format(name, lastname)

    print '|',
    print task_dict[id1].ljust(41),
    print '|',
    print '[{0}](/{1}/)'.format(id1, id1),
    print '|',
    print level1.ljust(11, ' '),
    print '|',
    print fullname.ljust(17, ' '),
    print '|',
    print existuje(id1).ljust(8),
    print '|'
    print '|',
    print task_dict[id2].ljust(41),
    print '|',
    print '[{0}](/{1}/)'.format(id2, id2),
    print '|',
    print level2.ljust(11, ' '),
    print '|',
    print fullname.ljust(17, ' '),
    print '|',
    print existuje(id2).ljust(8),
    print '|'
