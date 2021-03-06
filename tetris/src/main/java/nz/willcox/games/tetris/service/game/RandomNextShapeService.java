package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.factory.BackLFactory;
import nz.willcox.games.tetris.factory.FrontLFactory;
import nz.willcox.games.tetris.factory.LongFactory;
import nz.willcox.games.tetris.factory.SFactory;
import nz.willcox.games.tetris.factory.ShapeFactory;
import nz.willcox.games.tetris.factory.SquareFactory;
import nz.willcox.games.tetris.factory.TriangleFactory;
import nz.willcox.games.tetris.factory.ZFactory;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RandomNextShapeService {

    private final List<ShapeFactory> shapeFactories;

    @Inject
    public RandomNextShapeService(
            BackLFactory backLFactory,
            FrontLFactory frontLFactory,
            LongFactory longFactory,
            SFactory sFactory,
            SquareFactory squareFactory,
            TriangleFactory triangleFactory,
            ZFactory zFactory
    ) {
        shapeFactories = new ArrayList<>();
        shapeFactories.add(backLFactory);
        shapeFactories.add(frontLFactory);
        shapeFactories.add(longFactory);
        shapeFactories.add(sFactory);
        shapeFactories.add(squareFactory);
        shapeFactories.add(triangleFactory);
        shapeFactories.add(zFactory);
    }

    public TetrisShape createRandomShape() {
        final ShapeFactory randomShapeFactory = getRandomShapeFactory();
        return randomShapeFactory.createTetrisShape(createStartMidLocation());
    }

    private ShapeFactory getRandomShapeFactory() {
        final int randomShapeFactoryIndex = (int) (Math.random() * shapeFactories.size());
        return shapeFactories.get(randomShapeFactoryIndex);
    }

    private LocationPoint createStartMidLocation() {
        return new LocationPoint.Builder()
                .topX(Constants.MID_TOP_X)
                .topY(Constants.MID_TOP_Y)
                .build();
    }
}
